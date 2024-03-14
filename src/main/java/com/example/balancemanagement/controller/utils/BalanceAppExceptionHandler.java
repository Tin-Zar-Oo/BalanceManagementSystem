package com.example.balancemanagement.controller.utils;

import com.example.balancemanagement.domain.exception.BalanceAppException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.RequestContextUtils;
@ControllerAdvice
public class BalanceAppExceptionHandler {
    @ExceptionHandler(value = BalanceAppException.class)
    public String handle(BalanceAppException e, HttpServletRequest request){
        RequestContextUtils.getOutputFlashMap(request).put("message",e.getMessage());
        System.out.println(e.getMessage());
        return "redirect:/";
    }
}
