package com.example.balancemanagement.service;

import com.example.balancemanagement.dao.BalanceItemDao;
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
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BalanceService {
    @Autowired
    private BalanceItemDao itemDao;
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

    public BalanceEditForm fetchForm(Integer id) {
        return null;
    }
}
