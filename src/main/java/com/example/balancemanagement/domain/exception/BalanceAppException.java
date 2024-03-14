package com.example.balancemanagement.domain.exception;

public class BalanceAppException extends RuntimeException{


    private static final long serialVersionUID = 1L;
    public BalanceAppException(String message) {
        super(message);
    }
}
