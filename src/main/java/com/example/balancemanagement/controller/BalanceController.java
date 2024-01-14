package com.example.balancemanagement.controller;



import com.example.balancemanagement.model.entity.Balance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
@Controller
@RequestMapping("user/balance")
public class BalanceController {

    public String addNew() {

        return "";
    }
     public String edit(int id) {

        return "";
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