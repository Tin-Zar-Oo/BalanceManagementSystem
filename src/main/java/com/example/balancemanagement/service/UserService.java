package com.example.balancemanagement.service;

import com.example.balancemanagement.dao.UserDao;
import com.example.balancemanagement.domain.entity.User;
import com.example.balancemanagement.domain.form.SignUpForm;
import com.example.balancemanagement.domain.vo.UserVO;
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


    public UserVO findByLoginId(String username) {
     return userDao.findOneByLoginId(username).map(UserVO::new).orElseThrow();
    }
}
