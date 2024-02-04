package com.example.balancemanagement.dao;

import com.example.balancemanagement.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {
    Optional<User> findByLoginId(String username);

    Optional<User> findOneByLoginId(String username);
}
