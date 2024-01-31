package com.example.balancemanagement.dao;

import com.example.balancemanagement.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceDao extends JpaRepository<Balance,Integer> {
}
