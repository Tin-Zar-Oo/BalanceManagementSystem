package com.example.balancemanagement.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ChangePasswordForm {

    private String loginId;
    private String oldPassword;
    private String newPassword;

    public String getLoginId() {
        return loginId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}