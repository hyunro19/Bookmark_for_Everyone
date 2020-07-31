package com.hyunro.bookmark.config;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .cors().and()
                .csrf().disable().headers().frameOptions().disable()// h-2console화면을 사용하기 위해 해당 옵션들 disable
                .httpStrictTransportSecurity().disable()
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();

    }

}
