//package com.example.demo.config.security2;
//
//import com.example.demo.enums.ResultCode;
//import com.example.demo.model.AjaxResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @author: fcx
// * @create: 2018-12-02 09:27
// * @description:
// */
//@Component("failureAuthentication")
//public class FailureAuthentication extends SimpleUrlAuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                        AuthenticationException exception) throws IOException, ServletException {
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        AjaxResult result = new AjaxResult(ResultCode.User.LOGIN_FAILED.code, "登录失败");
//        ObjectMapper mapper = new ObjectMapper();
//        writer.println(mapper.writeValueAsString(result));
//        writer.flush();
//        writer.close();
//    }
//}
