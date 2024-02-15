package com.example.balancemanagement.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class BalanceAccessListener {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @EventListener
    void onSuccess(AuthenticationSuccessEvent event){
    var time = LocalDateTime.ofInstant(
            new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
    var username  = event.getAuthentication().getName();
    log.info("{} is sign in at {}.",username,time);
    }

    @EventListener
    void onFailure(AbstractAuthenticationFailureEvent event){
        var time = LocalDateTime.ofInstant(
                new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
        var username  = event.getAuthentication().getName();
        log.info("{} is fail to sign in at {} because of {}.",username,time,event.getException().getMessage());
    }


}
