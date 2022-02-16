package com.example.demo.config.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.filter.CorsFilter;

/**
 * Security 安全认证相关配置
 * Oauth2依赖于Security 默认情况下WebSecurityConfig执行比ResourceServerConfig优先
 *
 * @EnableGlobalMethodSecurity(securedEnabled=true) 开启@Secured 注解过滤权限
 * @EnableGlobalMethodSecurity(jsr250Enabled=true) 开启@RolesAllowed 注解过滤权限
 * @EnableGlobalMethodSecurity(prePostEnabled=true) 使用表达式时间方法级别的安全性 4个注解可用
 * -@PreAuthorize 在方法调用之前,基于表达式的计算结果来限制对方法的访问
 * -@PostAuthorize 允许方法调用,但是如果表达式计算结果为false,将抛出一个安全性异常
 * -@PostFilter 允许方法调用,但必须按照表达式来过滤方法的结果
 * -@PreFilter 允许方法调用,但必须在进入方法之前过滤输入值
 */
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    public WebSecurityConfig(CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 跨域配置
                .addFilterBefore(corsFilter, FilterSecurityInterceptor.class)

                .authorizeRequests()
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable();
    }
}
