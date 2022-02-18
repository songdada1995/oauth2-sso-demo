package com.example.demo.config.security;

import com.example.demo.constant.CacheConstants;
import com.example.demo.constant.SecurityConstants;
import com.example.demo.domain.security.LoginUser;
import com.example.demo.service.security.RedisClientDetailsService;
import com.example.demo.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenEnhancer tokenEnhancer;

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        /* 配置token获取和验证时的策略 */
        security.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详情
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 定义授权和令牌端点以及令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints
//                // 请求方式
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                // 指定token存储位置
//                .tokenStore(tokenStore())
//                // 自定义令牌增强器
//                .tokenEnhancer(tokenEnhancer)
//                // 用户账号密码认证
//                .userDetailsService(userDetailsService)
//                // 指定认证管理器
//                .authenticationManager(authenticationManager)
//                // 是否重复使用 refresh_token
//                .reuseRefreshTokens(false)
//                // 自定义异常处理
//                .exceptionTranslator(new CustomWebResponseExceptionTranslator());

        endpoints.accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(jwtTokenStore());

    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("MyJWTSecret");   //  Sets the JWT signing key
        return jwtAccessTokenConverter;
    }


    /**
     * 声明 ClientDetails实现
     */
    public RedisClientDetailsService clientDetailsService() {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        return clientDetailsService;
    }

    /**
     * 基于 Redis 实现，令牌保存到缓存
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }

    /**
     * 自定义令牌增强器
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (authentication.getUserAuthentication() != null) {
                Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
                LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
                additionalInformation.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }
}
