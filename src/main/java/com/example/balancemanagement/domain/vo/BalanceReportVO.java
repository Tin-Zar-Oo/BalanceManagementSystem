package com.example.balancemanagement.domain.vo;

import com.example.balancemanagement.domain.entity.Balance.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class BalanceReportVO {
    private int id;
    private LocalDate date;
    private Type type;
    private String category;
    private int amount;
    private int balance;

}