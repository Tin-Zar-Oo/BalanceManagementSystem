package com.example.balancemanagement.controller;

import com.example.balancemanagement.controller.utils.Pagination;
import com.example.balancemanagement.service.UserAccessLogService;
import com.example.balancemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("user/profile")
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccessLogService accessLogService;
    @GetMapping
    String index(ModelMap model,
               @RequestParam  Optional<Integer> page,
               @RequestParam  Optional<Integer> size){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var userVO = userService.findByLoginId(username);
        model.put("user",userVO);
        var accessLog = accessLogService.search(username,page,size);
        model.put("list",accessLog.getContent());
        var pagination = Pagination.builder()
                .url("/user/profile")
                .current(accessLog.getNumber())
                .total(accessLog.getTotalPages())
                .first(accessLog.isFirst())
                .last(accessLog.isLast()).build();
        model.put("pagination",pagination);
        return "profile";
    }

    @PostMapping("contact")
    String updateContact(@RequestParam(required = false) String phone, @RequestParam(required = false) String email){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateContact(username,phone,email);
        return "redirect:/user/profile";
    }
}
