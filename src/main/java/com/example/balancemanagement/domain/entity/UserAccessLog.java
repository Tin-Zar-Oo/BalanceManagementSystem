package com.example.balancemanagement.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class UserAccessLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private Type type;
    private LocalDateTime accessTime;
    private String errorMessage;

    public enum Type{
        Signin, Signout, Error
    }

    public UserAccessLog(){}

    public UserAccessLog(String username, Type type, LocalDateTime accessTime) {
        this.username = username;
        this.type = type;
        this.accessTime = accessTime;
    }

    public UserAccessLog(String username, Type type, LocalDateTime accessTime, String errorMessage) {
        this.username = username;
        this.type = type;
        this.accessTime = accessTime;
        this.errorMessage = errorMessage;
    }
}
