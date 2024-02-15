package com.example.balancemanagement.domain.vo;

import com.example.balancemanagement.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserVO {

    private int id;
    private String loginId;
    private String name;
    private boolean status;
    private String phone;
    private String email;

    public UserVO(){}


    public UserVO(User entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.status = entity.isActive();
    }
}