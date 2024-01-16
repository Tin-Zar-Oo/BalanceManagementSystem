package com.example.balancemanagement.controller;



import com.example.balancemanagement.model.entity.Balance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
@Controller
@RequestMapping("user/balance")
public class BalanceController {
    @GetMapping("add/{type}")
    public String addNew(@PathVariable String type, ModelMap model) {
        model.put("title","incomes".equals(type) ? "Add New Income" : "Add New Expense");
        model.put("type",type);
        return "balance-edit";
    }
    @GetMapping("edit/{id:\\d+}")
     public String edit(@PathVariable int id,ModelMap model) {
        model.put("title","Edit Income");
        model.put("type","incomes");
        return "balance-edit";
    }
    public String save(Balance balance) {

        return "";
    }

    @GetMapping("{id:\\d+}")
    public String findById(@PathVariable int id) {
        System.out.println("Balance Id : %d".formatted(id));
        return "balance-details";
    }

    public String search(Balance.Type type, String category, LocalDate from, LocalDate to) {

        return "";
    }
      public String delete(int id) {

        return "";
    }

}