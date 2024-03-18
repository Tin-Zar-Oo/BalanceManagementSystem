package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.entity.Balance.Type;
import com.example.balancemanagement.domain.exception.BalanceAppException;
import com.example.balancemanagement.domain.form.BalanceEditForm;
import com.example.balancemanagement.domain.form.BalanceItemForm;
import com.example.balancemanagement.domain.form.BalanceSummaryForm;
import com.example.balancemanagement.service.BalanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
         var result = service.findById(id);
         form.setHeader(result.getHeader());
         form.setItems(result.getItems());
        }
        if( null != type && form.getHeader().getType() != type ){
        form.setHeader(new BalanceSummaryForm());
        form.getHeader().setType(type);
        form.getItems().clear();
        }

        return "balance-edit";
    }



    @PostMapping("item")
    public String addItem(
            @ModelAttribute("balanceEditForm") BalanceEditForm form,
            @ModelAttribute("itemForm") @Valid BalanceItemForm itemForm,
            BindingResult result){
        if( result.hasErrors()){
            return "balance-edit";
        }
        form.getItems().add(itemForm);
        return "redirect:/user/balance-edit?%s".formatted(form.queryParams());
    }

    @GetMapping("item")
    public String deleteItem(@ModelAttribute("balanceEditForm") BalanceEditForm form,@RequestParam int index){
        var item = form.getItems().get(index);
        if(item.getId() == 0){
            form.getItems().remove(item);
        } else {
            item.setDeleted(true);
        }
        return "redirect:/user/balance-edit?%s".formatted(form.queryParams());

    }

    @GetMapping("confirm")
    public String confirm(){
        return "balance-edit-confirm";
    }

    @PostMapping("")
    public String save(@ModelAttribute("balanceEditForm") BalanceEditForm form,
                       @ModelAttribute("summaryForm") @Valid BalanceSummaryForm summaryForm, BindingResult result) {
        if( result.hasErrors()){
            return "balance-edit-confirm";
        }

        form.getHeader().setCategory(summaryForm.getCategory());
        form.getHeader().setDate(summaryForm.getDate());
        var id = service.save(form);
        form.clear();
        return "redirect:/user/balance/details/%d".formatted(id);
    }

    @ModelAttribute("summaryForm")
    BalanceSummaryForm summaryForm(){
        return new BalanceSummaryForm();
    }
    @ModelAttribute("itemForm")
    public BalanceItemForm itemForm(){
        return new BalanceItemForm();
    }

    @ModelAttribute("balanceEditForm")
    public BalanceEditForm form(@RequestParam(required = false) Integer id, @RequestParam(required = false) Type type){
        if( null != id){
            return service.findById(id);
        }
        if( null == type){
            throw new BalanceAppException("Please set type for balance");
        }
        return new BalanceEditForm().type(type);
    }

}
