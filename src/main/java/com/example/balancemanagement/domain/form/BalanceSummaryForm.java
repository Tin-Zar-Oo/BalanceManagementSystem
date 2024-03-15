package com.example.balancemanagement.domain.form;


import com.example.balancemanagement.domain.entity.Balance.Type;


import java.io.Serializable;
import java.time.LocalDate;

public class BalanceSummaryForm implements Serializable {
    private int id;
    private LocalDate date;
    private String category;
    private Type type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}