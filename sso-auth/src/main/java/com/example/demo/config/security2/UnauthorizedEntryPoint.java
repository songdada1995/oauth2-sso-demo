//package com.example.demo.config.security2;
//
//import com.example.demo.enums.ResultCode;
//import com.example.demo.model.AjaxResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//
//import static com.example.demo.model.AjaxResult.DATA_TAG;
//
///**
// * @description:
// * @author: fengchangxin
// * @create: 2019-06-10 15:43
// */
//@Component("unauthorizedEntryPoint")
//public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//        Map<String, String[]> paramMap = request.getParameterMap();
//        StringBuilder param = new StringBuilder();
//        paramMap.forEach((k, v) -> {
//            param.append("&").append(k).append("=").append(v[0]);
//        });
//        param.deleteCharAt(0);
//        String isRedirectValue = request.getParameter("isRedirect");
//        if (StringUtils.isNoneEmpty(isRedirectValue) && Boolean.valueOf(isRedirectValue)) {
//            System.out.println("http://oauth.web.com/authPage/#/login?" + param.toString());
//            response.sendRedirect("http://oauth.web.com/authPage/#/login?" + param.toString());
//            return;
//        }
//        String authUrl = "http://oauth.com/auth/oauth/authorize?" + param.toString() + "&isRedirect=true";
//        System.out.println("******" + authUrl);
//        AjaxResult result = new AjaxResult(ResultCode.User.NOT_SIGN_IN.code, "未登录");
//        result.put(DATA_TAG, authUrl);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        writer.print(mapper.writeValueAsString(result));
//        writer.flush();
//        writer.close();
//    }
//}
