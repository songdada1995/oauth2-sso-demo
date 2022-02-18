package com.example.demo.controller;

import com.example.demo.model.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public AjaxResult hello() {
        return AjaxResult.success("执行成功","hello client1");
    }

}
