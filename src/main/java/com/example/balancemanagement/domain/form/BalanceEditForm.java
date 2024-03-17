package com.example.balancemanagement.domain.form;

import com.example.balancemanagement.domain.entity.Balance;
import com.example.balancemanagement.domain.entity.Balance.Type;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BalanceEditForm implements Serializable {
    private BalanceSummaryForm header;
    private List<BalanceItemForm> items;

    public BalanceEditForm() {
        header = new BalanceSummaryForm();
        items = new ArrayList<>();
    }

    public BalanceEditForm type(Type type){
    header.setType(type);
    return this;
    }

    public BalanceSummaryForm getHeader() {
        return header;
    }

    public void setHeader(BalanceSummaryForm header) {
        this.header = header;
    }

    public List<BalanceItemForm> getItems() {
        return items;
    }

    public void setItems(List<BalanceItemForm> items) {
        this.items = items;
    }

    public int getTotal() {
     return items.stream().mapToInt(a -> a.getQuantity() * a.getUnitPrice()).sum();
         }

    public boolean isShowSaveButton(){
        return !items.isEmpty();
    }

    public String queryParams() {
        return header.getId() == 0 ? "type=%s".formatted(header.getType()) : "id=%s".formatted(header.getId());
    }


    //after delete dont want to show in ui
    public List<BalanceItemForm> getValidItems()
    {
        return items.stream().filter(a -> !a.isDeleted()).toList();
    }

    public void clear() {
        header = new BalanceSummaryForm();
        items.clear();
    }
}