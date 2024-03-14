package com.example.balancemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/home")
public class HomeController {
    @GetMapping
    String index(  @ModelAttribute("message") final Object messageArg, Model model){
        if (messageArg != null && messageArg instanceof String ) {
            System.out.println("messageArg is "+(String)messageArg);
            model.addAttribute("message",messageArg);
        } else {
            System.out.println("messageArg is null");
        }
    return "home";
}





}
