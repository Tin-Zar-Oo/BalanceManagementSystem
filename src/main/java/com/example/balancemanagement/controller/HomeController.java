package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.form.ChangePasswordForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/home")
public class HomeController {
    @GetMapping
    String index(@ModelAttribute("message") final Object messageArg, Model model, ChangePasswordForm form){

        if (messageArg != form.getOldPassword() && messageArg instanceof String ) {
            System.out.println("messageArg is "+(String)messageArg);
            model.addAttribute("message",messageArg);
        } else {
            System.out.println("Please enter old password.");
        }
    return "home";
}





}
