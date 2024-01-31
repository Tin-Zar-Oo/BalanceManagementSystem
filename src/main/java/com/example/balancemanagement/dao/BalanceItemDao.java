package com.example.balancemanagement.dao;

import com.example.balancemanagement.domain.entity.BalanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceItemDao extends JpaRepository<BalanceItem,Integer> {
}
