//package com.example.demo.config.security2;
//
//import com.example.demo.enums.ResultCode;
//import com.example.demo.model.AjaxResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
// * @create: 2018-12-02 09:24
// * @description:
// */
//@Component("successAuthentication")
//public class SuccessAuthentication extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws ServletException, IOException {
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        AjaxResult result = new AjaxResult(ResultCode.User.LOGIN_SUCCESS.code, "登录成功");
//        ObjectMapper mapper = new ObjectMapper();
//        writer.println(mapper.writeValueAsString(result));
//        writer.flush();
//        writer.close();
//    }
//}
