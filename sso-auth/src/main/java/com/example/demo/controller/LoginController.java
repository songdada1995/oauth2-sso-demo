//package com.example.demo.controller;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//public class LoginController {
//
//    @Resource
//    private TokenStore tokenStore;
//
//    /**
//     * 删除token
//     *
//     * @param authHeader
//     * @return
//     */
//    @DeleteMapping("/logout")
//    public String logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
//        if (StringUtils.isEmpty(authHeader)) {
//            return "empty";
//        }
//
//        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
//        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
//            return "empty";
//        }
//
//        // 清空 access token
//        tokenStore.removeAccessToken(accessToken);
//
//        // 清空 refresh token
//        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
//        tokenStore.removeRefreshToken(refreshToken);
//
//        return "success";
//    }
//}
