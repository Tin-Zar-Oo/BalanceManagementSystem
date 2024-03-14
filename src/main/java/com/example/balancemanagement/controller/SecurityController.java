package com.example.balancemanagement.controller;

import com.example.balancemanagement.domain.entity.User.Role;
import com.example.balancemanagement.domain.form.ChangePasswordForm;
import com.example.balancemanagement.domain.form.SignUpForm;
import com.example.balancemanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

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
    public RedirectView changePass(
            @ModelAttribute ChangePasswordForm form,
            RedirectAttributes redirect) {

        System.out.println("LoginId "+form.getLoginId());
        System.out.println("Old password "+form.getOldPassword());
        System.out.println("New password "+form.getOldPassword());
        redirect.addFlashAttribute("message","Your password has been changed successfully.");
        redirect.addFlashAttribute("message2","Can you see this?.");

        //userService.changePassword(form);
        //return "redirect:/";
        return new RedirectView("/user/home", true);
    }

//    @GetMapping("/test2")
//    public String test2(HttpServletRequest request,
//                        @ModelAttribute("message") final Object messageArg
//    ){
//        if (messageArg != null) {
//            System.out.println("messageArg is "+(String)messageArg);
//        } else {
//            System.out.println("messageArg is null");
//        }
//
//        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
//        if (inputFlashMap != null) {
//            String message = (String) inputFlashMap.get("message");
//            System.out.println("message is "+message);
//            return "test2";
//        }
//        else {
//            System.out.println("flashmap is null");
//            return "test2";
//        }
//    }

    @ModelAttribute(name ="form")
    SignUpForm form(){
        return new SignUpForm();
    }

}