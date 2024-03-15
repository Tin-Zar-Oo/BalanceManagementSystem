package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.entity.Balance;
import com.example.balancemanagement.domain.exception.BalanceAppException;
import com.example.balancemanagement.domain.form.BalanceEditForm;
import com.example.balancemanagement.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user/balance-edit")
@SessionAttributes("balanceEditForm")
public class BalanceEditController {

    @Autowired
    private BalanceService service;

    @GetMapping
    public String edit() {

        return "balance-edit";
    }
    @PostMapping("")
    public String save() {

        return "redirect:/user/balance/%d".formatted(1);
    }

    @ModelAttribute("balanceEditForm")
    public BalanceEditForm form(@RequestParam(required = false) Integer id, @RequestParam(required = false) Balance.Type type){
        if( null != id){
            return service.fetchForm(id);
        }
        if( null == type){
            throw new BalanceAppException("Please set type for balance");
        }
        return new BalanceEditForm().type(type);
    }

}
