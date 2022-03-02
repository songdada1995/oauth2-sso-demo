package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class CallbackController {

    @Value("${client-web-url}")
    private String clientWebUrl;

    @GetMapping("/")
    public void callback(HttpServletResponse response) throws IOException {
        log.info("重定向到client1前端home页面：" + clientWebUrl + "/client1Page/#/home");
        response.sendRedirect(clientWebUrl + "/client1Page/#/home");
    }

}
