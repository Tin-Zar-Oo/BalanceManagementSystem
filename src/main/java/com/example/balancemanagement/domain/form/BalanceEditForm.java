package com.example.balancemanagement.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class BalanceEditForm {
    private BalanceSummaryForm header;
    private List<BalanceItemForm> items;
    public int getTotal() {

        return 0;
    }

}