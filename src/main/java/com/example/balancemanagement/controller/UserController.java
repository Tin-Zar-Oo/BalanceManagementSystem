package com.example.balancemanagement.controller;


import com.example.balancemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public String search(@RequestParam (required = false) Boolean status,
                         @RequestParam (required = false) String name,
                         @RequestParam (required = false) String phone,
                         ModelMap model) {
        var list = userService.search(status,name,phone);
        model.put("users",list);
        System.out.println("User list:: "+list);
        System.out.println("Status: %s".formatted(status));
        System.out.println("Name: %s".formatted(name));
        System.out.println("Phone: %s".formatted(phone));
        return "users";
    }

    @PostMapping("status")
    public String changeStatus(
            @RequestParam int id,
            @RequestParam boolean status
    ){
        userService.changeStatus(id,!status);
        return  "redirect:/admin/users";
    }

}