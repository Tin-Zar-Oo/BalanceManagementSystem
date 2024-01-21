package com.example.balancemanagement.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private int id;
    private String name;
    private boolean status;
    private String phone;
    private String email;

}