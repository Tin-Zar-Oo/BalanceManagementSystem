package com.example.balancemanagement.security;

import com.example.balancemanagement.dao.UserAccessLogDao;
import com.example.balancemanagement.domain.entity.UserAccessLog;
import com.example.balancemanagement.domain.entity.UserAccessLog.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class BalanceAccessListener {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserAccessLogDao userAccessLogDao;


    @EventListener
    @Transactional
    public void onSuccess(AuthenticationSuccessEvent event){
    var time = LocalDateTime.ofInstant(
            new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
    var username  = event.getAuthentication().getName();
    log.info("{} is sign in at {}.",username,time);
    userAccessLogDao.save(new UserAccessLog(username,Type.Signin,time));
    }

    @EventListener
    @Transactional
    public void onFailure(AbstractAuthenticationFailureEvent event){
        var time = LocalDateTime.ofInstant(
                new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
        var username  = event.getAuthentication().getName();
        log.info("{} is fail to sign in at {} because of {}.",username,time,event.getException().getMessage());
        userAccessLogDao.save(new UserAccessLog(username,Type.Error,time,event.getException().getMessage()));

    }

    @EventListener
    @Transactional
    public void onSessionDestroy(HttpSessionDestroyedEvent event){
    event.getSecurityContexts().stream().findAny()
            .ifPresent(auth -> {
            var time = LocalDateTime.ofInstant(
                      new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
            var username = auth.getAuthentication().getName();
            log.info("{} is sign out at {}.",username,time);
                userAccessLogDao.save(new UserAccessLog(username,Type.Signout,time));

            });
    }


}
