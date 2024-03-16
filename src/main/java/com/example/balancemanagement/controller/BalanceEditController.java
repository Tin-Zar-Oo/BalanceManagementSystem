package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.entity.Balance.Type;
import com.example.balancemanagement.domain.exception.BalanceAppException;
import com.example.balancemanagement.domain.form.BalanceEditForm;
import com.example.balancemanagement.domain.form.BalanceItemForm;
import com.example.balancemanagement.domain.form.BalanceSummaryForm;
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
    public String edit(@ModelAttribute("balanceEditForm") BalanceEditForm form,
                       @RequestParam(required = false) Integer id,
                       @RequestParam(required = false) Type type) {
        if( null != id && form.getHeader().getId() != id){
         var result = service.fetchForm(id);
         form.setHeader(result.getHeader());
         form.setItems(result.getItems());
        }
        if( null != type && !form.getHeader().getType().equals(type)){
        form.setHeader(new BalanceSummaryForm());
        form.getHeader().setType(type);
        form.getItems().clear();
        }

        return "balance-edit";
    }

    @PostMapping("item")
    public String addItem(
            @ModelAttribute("balanceEditForm") BalanceEditForm form,
            @ModelAttribute("itemForm") BalanceItemForm itemForm){
        form.getItems().add(itemForm);
        var queryParams = form.getHeader().getId() == 0 ? "type=%s".formatted(form.getHeader().getType()) : "id=%s".formatted(form.getHeader().getId());
        return "redirect:/user/balance-edit?%s".formatted(queryParams);
    }

    @GetMapping("confirm")
    public String confirm(){
        return "balance-edit-confirm";
    }
    @PostMapping("")
    public String save() {

        return "redirect:/user/balance/%d".formatted(1);
    }

    @ModelAttribute("itemForm")
    public BalanceItemForm itemForm(){
        return new BalanceItemForm();
    }

    @ModelAttribute("balanceEditForm")
    public BalanceEditForm form(@RequestParam(required = false) Integer id, @RequestParam(required = false) Type type){
        if( null != id){
            return service.fetchForm(id);
        }
        if( null == type){
            throw new BalanceAppException("Please set type for balance");
        }
        return new BalanceEditForm().type(type);
    }

}
