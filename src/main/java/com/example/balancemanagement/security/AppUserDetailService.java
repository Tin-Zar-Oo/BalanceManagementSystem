package com.example.balancemanagement.security;

import com.example.balancemanagement.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userDao.findOneByloginId(username)
              .map(user -> User.withUsername(username)
                      .password(user.getPassword())
                      .authorities(AuthorityUtils.createAuthorityList(user.getRole().name()))
                      .disabled(!user.isActive())
                      .accountExpired(!user.isActive())
                      .build()).orElseThrow(() -> new UsernameNotFoundException("There is no user with login Id %s".formatted(username)));

    }
}
