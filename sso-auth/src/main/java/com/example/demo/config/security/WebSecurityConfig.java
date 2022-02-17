package com.example.demo.config.security;

import com.example.demo.config.security.handler.SecurityAccessDeniedHandler;
import com.example.demo.config.security.handler.SecurityAuthenticationEntryPoint;
import com.example.demo.config.security.handler.SecurityAuthenticationFailureHandler;
import com.example.demo.config.security.handler.SecurityAuthenticationSuccessHandler;
import com.example.demo.service.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.filter.CorsFilter;

/**
 * Security 安全认证相关配置
 * Oauth2依赖于Security 默认情况下WebSecurityConfig执行比ResourceServerConfig优先
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;

    private final SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;

    private final SecurityAccessDeniedHandler securityAccessDeniedHandler;

    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl,
                             SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler,
                             SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler,
                             SecurityAccessDeniedHandler securityAccessDeniedHandler,
                             SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint,
                             CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.securityAuthenticationSuccessHandler = securityAuthenticationSuccessHandler;
        this.securityAuthenticationFailureHandler = securityAuthenticationFailureHandler;
        this.securityAccessDeniedHandler = securityAccessDeniedHandler;
        this.securityAuthenticationEntryPoint = securityAuthenticationEntryPoint;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assets/**", "/css/**", "/images/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // 跨域配置
                .addFilterBefore(corsFilter, FilterSecurityInterceptor.class)

                .authorizeRequests()
                .antMatchers(
                        "/oauth/**",
                        "/logout",
                        "/login",
                        "/test/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                // 异常处理
                .exceptionHandling()
                // 没有登录的处理
                .authenticationEntryPoint(securityAuthenticationEntryPoint)
                // 没有权限的处理
                .accessDeniedHandler(securityAccessDeniedHandler)
                .and()
                // 表单登录
                .formLogin()
                // 登录成功处理
                .successHandler(securityAuthenticationSuccessHandler)
                // 登录失败处理
                .failureHandler(securityAuthenticationFailureHandler)
                .and().csrf().disable();
    }
}
