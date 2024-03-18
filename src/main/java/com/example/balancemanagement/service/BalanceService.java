package com.example.balancemanagement.service;

import com.example.balancemanagement.dao.BalanceDao;
import com.example.balancemanagement.dao.BalanceItemDao;
import com.example.balancemanagement.dao.UserDao;
import com.example.balancemanagement.domain.entity.Balance;
import com.example.balancemanagement.domain.entity.BalanceItem;
import com.example.balancemanagement.domain.form.BalanceEditForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BalanceService {
    @Autowired
    private BalanceItemDao itemDao;

    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private UserDao userDao;
    @PreAuthorize("authenticated()")
    public Page<BalanceItem> searchItem(Balance.Type type, LocalDate dateFrom, LocalDate dateTo, String keyword, Optional<Integer> page, Optional<Integer> size) {

        var pageInfo = PageRequest.of(page.orElse(0),size.orElse(10));

        //login user specification
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        Specification<BalanceItem> spec = (root, query, builder) ->
                builder.equal(root.get("balance").get("user").get("loginId")
                        , username);

        // type specification
        spec = spec.and((root, query, builder) ->
                builder.equal(root.get("balance").get("type"),
                        type));

        // date from specification
        if( null != dateFrom){
        spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("balance").get("date")
                , dateFrom));
        }
        // date to specification
        if( null != dateTo){
            spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("balance").get("date"),
                    dateTo));
        }
        // keyword specification
        if(StringUtils.hasLength(keyword)){
        Specification<BalanceItem> category = (root, query, builder) ->
                builder.like(builder.lower(root.get("balance").get("category")), "%%%s%%".formatted(keyword.toLowerCase()));
            Specification<BalanceItem> item = (root, query, builder) ->
                    builder.like(builder.lower(root.get("item")), "%%%s%%".formatted(keyword.toLowerCase()));
            spec = spec.and(item.or(category));
        }

        return itemDao.findAll(spec, pageInfo);
    }

    public BalanceEditForm findById(Integer id) {
      return balanceDao.findById(id).map(BalanceEditForm::new).orElseThrow();

    }

    @Transactional
    public int save(BalanceEditForm form) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userDao.findOneByLoginId(username).orElseThrow();

        var balance = form.getHeader().getId() == 0 ? new Balance()
                : balanceDao.findById(form.getHeader().getId()).orElseThrow();

       balance.setUser(user);
       balance.setCategory(form.getHeader().getCategory());
       balance.setDate(form.getHeader().getDate());
       balance.setType(form.getHeader().getType());

       balance = balanceDao.save(balance);
       for( var formItem : form.getItems()){
          var item = formItem.getId() == 0 ? new BalanceItem() : itemDao.findById(formItem.getId()).orElseThrow();
          if(formItem.isDeleted()){
              itemDao.delete(item);
              continue;
          }
          item.setItem(formItem.getItem());
          item.setUnitPrice(formItem.getUnitPrice());
          item.setQuantity(formItem.getQuantity());
          item.setBalance(balance);

          itemDao.save(item);
       }
        return balance.getId();
    }
}
