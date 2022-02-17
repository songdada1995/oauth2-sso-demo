package com.example.demo.config.security.handler;

import com.example.demo.enums.ResultCode;
import com.example.demo.model.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功处理
 */
@Slf4j
@Component
public class SecurityAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        logger.info("登录成功");
        logger.info(authentication.toString());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        AjaxResult result = new AjaxResult(ResultCode.User.LOGIN_SUCCESS.code, "登录成功");
        writer.println(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
