package com.example.balancemanagement.service;

import com.example.balancemanagement.dao.UserAccessLogDao;
import com.example.balancemanagement.domain.entity.UserAccessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccessLogService {

    @Autowired
    private UserAccessLogDao accessLogDao;
    public Page<UserAccessLog> search(String username, Optional<Integer> page, Optional<Integer> size) {
        var pageInfo = PageRequest.of(page.orElse(0), size.orElse(5)).withSort(Sort.by("accessTime").descending());
        Specification<UserAccessLog> spec = ((root, query, builder)
                -> builder.equal( root.get("username"),username  ));
        return accessLogDao.findAll(spec,pageInfo);

    }
}
