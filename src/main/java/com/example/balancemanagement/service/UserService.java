package com.example.balancemanagement.service;

import com.example.balancemanagement.dao.UserDao;
import com.example.balancemanagement.domain.entity.User;
import com.example.balancemanagement.domain.entity.User.Role;
import com.example.balancemanagement.domain.form.SignUpForm;
import com.example.balancemanagement.domain.vo.UserVO;
import org.codehaus.groovy.runtime.GStringUtil;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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


    @Transactional
    public void updateContact(String username, String phone, String email) {
        userDao.findOneByLoginId(username).ifPresent( user -> {
            user.setPhone(phone);
            user.setEmail(email);
        });
    }

    public List<UserVO> search(Boolean status, String name, String phone) {
        Specification<User> spec = (root, query, builder) -> builder.equal(root.get("role"),Role.Member );
        if(null != status){
         spec = spec.and((root, query, builder) -> builder.equal(root.get("active"),status));
        }
       if(StringUtils.hasLength(name)){
           spec = spec.and((root, query, builder) -> builder.like(builder.lower(root.get("name")),name.toLowerCase().concat("%")));
       }
       if(StringUtils.hasLength(phone)){
       spec = spec.and((root, query, builder) -> builder.like(root.get("phone"),phone.concat("%")));
       }
        return userDao.findAll(spec).stream().map(UserVO::new).toList();
    }


    @Transactional
    public void changeStatus(int id, boolean status) {
        userDao.findById(id).ifPresent(user -> user.setActive(status));
    }
}
