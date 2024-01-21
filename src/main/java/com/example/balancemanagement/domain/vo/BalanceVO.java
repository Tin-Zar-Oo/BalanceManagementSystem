package com.example.balancemanagement.domain.vo;

import com.example.balancemanagement.domain.form.BalanceItemForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class BalanceVO {
    private BalanceSummaryVO header;
    private List<BalanceItemForm> items;
    public int getTotal() {
        // TODO implement here
        return 0;
    }

}