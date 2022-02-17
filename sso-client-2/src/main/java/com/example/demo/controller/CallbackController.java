package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CallbackController {

    @Value("${client-web-url}")
    private String clientWebUrl;

    @GetMapping("/")
    public void callback(HttpServletResponse response) throws IOException {
        response.sendRedirect(clientWebUrl + "/client2Page/#/home");
    }

}
