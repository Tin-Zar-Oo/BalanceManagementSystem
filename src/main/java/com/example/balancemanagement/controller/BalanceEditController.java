package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.entity.Balance;
import com.example.balancemanagement.domain.form.BalanceEditForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user/balance-edit")
public class BalanceEditController {

    @GetMapping
    public String edit() {

        return "balance-edit";
    }
    @PostMapping("")
    public String save() {

        return "redirect:/user/balance/%d".formatted(1);
    }

    @ModelAttribute(name = "form")
    public BalanceEditForm form(@RequestParam(required = false) Integer id, @RequestParam(required = false) Balance.Type type){
        return null;
    }

}
