package com.example.balancemanagement.domain.entity;

import com.example.balancemanagement.domain.form.SignUpForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String loginId;
    private String phone;
    private String email;
    @Column(nullable = false)
    private Role role;
    private boolean active;
    public enum Role {
        Admin,
        Member
    }
    public User(){

    }
    public User(SignUpForm signUpForm){
    this.name = signUpForm.getName();
    this.loginId = signUpForm.getLoginId();
    this.password = signUpForm.getPassword();
    this.active = true;
    this.role = Role.Member;
    }



}