package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 访问得带上token
     *
     * @param authentication
     * @return
     */
    @GetMapping("/info")
    public Authentication info(Authentication authentication) {
        return authentication;
    }

    /**
     * 访问无需带token，ResourceServerConfig已设置
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "world";
    }
}
