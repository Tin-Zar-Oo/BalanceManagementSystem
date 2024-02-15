package com.example.balancemanagement.dao;

import com.example.balancemanagement.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByLoginId(String username);

    Optional<User> findOneByLoginId(String username);
}
