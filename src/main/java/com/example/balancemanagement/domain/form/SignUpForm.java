package com.example.balancemanagement.domain.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignUpForm {


    @NotBlank(message = "Enter member name.")
    private String name;
    @NotBlank(message = "Enter login Id.")
    private String loginId;
    @NotBlank(message = "Enter password.")
    private String password;

}