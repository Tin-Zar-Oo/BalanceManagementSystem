package com.example.balancemanagement.security;

import com.example.balancemanagement.dao.UserDao;
import com.example.balancemanagement.domain.entity.User;
import com.example.balancemanagement.domain.entity.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AppUserInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;

    @Transactional
    @EventListener(classes = ContextRefreshedEvent.class)
    public void initializeUser(){
     if( userDao.count() == 0){
         var user = new User();
         user.setName("Admin User");
         user.setLoginId("admin");
         user.setPassword(passwordEncoder.encode("admin"));
         user.setRole(Role.Admin);
         user.setActive(true);
         userDao.save(user);
     }
    }
}
