package com.example.balancemanagement.config;

import com.example.balancemanagement.domain.entity.User.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(form -> form.loginPage("/signin").defaultSuccessUrl("/"));

        http.logout(logout -> logout.logoutUrl("/signout").logoutSuccessUrl("/"));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/signin","/signup","/").permitAll()
                .requestMatchers("/user/**").hasAnyAuthority(Role.Member.name(),Role.Admin.name())
                .requestMatchers("/admin/**").hasAuthority(Role.Admin.name())
                .anyRequest().authenticated());
        http.exceptionHandling(ex -> ex.accessDeniedPage("/denied"));
                //.accessDeniedPage("/denied");
        return http.build();
    }

    @Bean
   AuthenticationEventPublisher authenticationEventPublisher(){
   return new DefaultAuthenticationEventPublisher();
   }

}
