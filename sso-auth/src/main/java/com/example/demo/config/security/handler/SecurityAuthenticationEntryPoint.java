package com.example.demo.config.security.handler;

import com.example.demo.enums.ResultCode;
import com.example.demo.model.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.example.demo.model.AjaxResult.DATA_TAG;

/**
 * 未登录处理
 */
@Slf4j
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Value("${auth-server-url}")
    private String authServerUrl;

    @Value("${auth-server-web-url}")
    private String authServerWebUrl;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("未登录");
        // 拼接url参数
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder param = new StringBuilder();
        if (MapUtils.isNotEmpty(paramMap)) {
            paramMap.forEach((k, v) -> param.append("&").append(k).append("=").append(v[0]));
            param.deleteCharAt(0);
        }

        String isRedirect = request.getParameter("is_redirect");
        if (StringUtils.isNoneEmpty(isRedirect) && Boolean.valueOf(isRedirect)) {
            log.info(authServerWebUrl + "/authPage/#/login?" + param.toString());
            response.sendRedirect(authServerWebUrl + "/authPage/#/login?" + param.toString());
            return;
        }

        // 未登录，返回授权url，带上参数is_redirect=true
        String authUrl = authServerUrl + "/oauth/authorize?" + param.toString() + "&is_redirect=true";
        log.info("*****" + authUrl);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        AjaxResult result = new AjaxResult(ResultCode.User.NOT_SIGN_IN.code, "未登录");
        result.put(DATA_TAG, authUrl);
        writer.println(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
