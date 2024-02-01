package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.entity.User.Role;
import com.example.balancemanagement.domain.form.SignUpForm;
import com.example.balancemanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if( auth != null &&
        auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.Admin.name())
        || a.getAuthority().equals(Role.Member.name()))){
            return "redirect:/user/home";
        }
        return "signup";
    }

    @GetMapping("signup")
    public void loadSignUp() {

    }
    @PostMapping("signup")
    public String signUp(@ModelAttribute(name ="form") @Valid SignUpForm form, BindingResult result) {
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "signup";
        }
        userService.signUp(form);
        System.out.println(form);
        return "signin";
    }

    @PostMapping("user/changepass")
    public String changePass(){
        return "redirect:/";
    }

    @ModelAttribute(name ="form")
    SignUpForm form(){
        return new SignUpForm();
    }

}