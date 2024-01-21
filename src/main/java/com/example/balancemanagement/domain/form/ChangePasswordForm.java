package com.example.balancemanagement.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordForm {

    private String loginId;
    private String oldPassword;
    private String newPassword;

}