package com.example.demo.config.security.handler;

import com.example.demo.enums.ResultCode;
import com.example.demo.model.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理
 */
@Slf4j
@Component
public class SecurityAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.error("登录失败");
        log.error(exception.toString());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        AjaxResult result = new AjaxResult(ResultCode.User.LOGIN_FAILED.code, "登录失败");
        writer.println(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
