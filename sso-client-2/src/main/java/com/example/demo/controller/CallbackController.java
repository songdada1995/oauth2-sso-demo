package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class CallbackController {

    @Value("${client-web-url}")
    private String clientWebUrl;

    @GetMapping("/")
    public void callback(HttpServletResponse response) throws IOException {
        log.info("重定向到client2前端home页面：" + clientWebUrl + "/client2Page/#/home");
        response.sendRedirect(clientWebUrl + "/client2Page/#/home");
    }

}
