package com.example.demo.controller;

import com.example.demo.model.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public AjaxResult demo() {
        return AjaxResult.success("hello demo");
    }

}
