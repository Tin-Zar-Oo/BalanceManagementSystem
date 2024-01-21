package com.example.balancemanagement.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class BalanceItemForm {
    private int id;
    private String item;
    private int unitPrice;
    private int quantity;

}