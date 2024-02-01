package com.example.balancemanagement.controller;
import com.example.balancemanagement.domain.entity.Balance.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
@Controller
@RequestMapping("user/balance")
public class BalanceController {

    @GetMapping
    String  report(){
        return "balance-report";
    }

    @GetMapping("{type}")
    public String incomes(ModelMap model,@PathVariable String type){
        model.put("title","incomes".equals(type) ?"Income Management" : "Expense Management");
        model.put("type",type);
        return "balance-list";
    }
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
    @PostMapping("")
    public String save() {

        return "redirect:/user/balance/%d".formatted(1);
    }

    @GetMapping("{id:\\d+}")
    public String findById(@PathVariable int id) {
        System.out.println("Balance Id : %d".formatted(id));
        return "balance-details";
    }
    @GetMapping("delete/{id:\\d+}")
    public String delete(@PathVariable int id) {

        return "redirect:/";
    }

    public String search(Type type, String category, LocalDate from, LocalDate to) {

        return "";
    }


}