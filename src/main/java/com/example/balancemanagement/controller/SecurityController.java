package com.example.balancemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
      public String signIn() {

        return "";
    }
    public String signUp() {

        return "";
    }
    @GetMapping("signout")
    public String signOut() {
        return "redirect:/signin";
    }

}