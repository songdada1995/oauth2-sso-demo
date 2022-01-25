package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public Authentication info(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public List<Map<String, String>> list() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("username", "宋大大");
        Map<String, String> map2 = new HashMap<>();
        map2.put("username", "石原里美");
        list.add(map1);
        list.add(map2);
        return list;
    }
}
