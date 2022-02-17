package com.example.demo.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // requestMatchers()配置的是哪些url进行安全控制，authorizeRequests()配置的是如何进行控制
        http.requestMatchers().antMatchers("/user/**")
                .and()
                .authorizeRequests()
                .antMatchers("/user/hello").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
