package com.example.balancemanagement.controller;

import com.example.balancemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user/profile")
public class ProfileController {
    @Autowired
    private UserService userService;
    @GetMapping
    String index(ModelMap model){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var userVO = userService.findByLoginId(username);
        model.put("user",userVO);
        return "profile";
    }

    @PostMapping("contact")
    String updateContact(@RequestParam String name, @RequestParam String phone){
        return null;
    }
}
