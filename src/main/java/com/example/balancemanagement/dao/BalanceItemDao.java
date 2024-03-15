package com.example.balancemanagement.dao;

import com.example.balancemanagement.domain.entity.BalanceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BalanceItemDao extends JpaRepository<BalanceItem,Integer> , JpaSpecificationExecutor<BalanceItem> {
}
