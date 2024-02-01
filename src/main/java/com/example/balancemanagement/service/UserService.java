package com.example.balancemanagement.service;

import com.example.balancemanagement.dao.UserDao;
import com.example.balancemanagement.domain.entity.User;
import com.example.balancemanagement.domain.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpForm form) {
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        userDao.save(new User(form));
    }
}
