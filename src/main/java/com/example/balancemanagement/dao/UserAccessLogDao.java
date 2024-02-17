package com.example.balancemanagement.dao;

import com.example.balancemanagement.domain.entity.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserAccessLogDao extends JpaRepository<UserAccessLog,Integer>, JpaSpecificationExecutor<UserAccessLog> {
}
