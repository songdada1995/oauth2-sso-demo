//package com.example.demo.config.security2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//
//@EnableAuthorizationServer
//@Configuration
//public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients()
//                .checkTokenAccess("isAuthenticated()")
//                .tokenKeyAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(inMemoryClientDetailsService());
//    }
//
//
//    @Bean
//    public ClientDetailsService inMemoryClientDetailsService() throws Exception {
//        return new InMemoryClientDetailsServiceBuilder()
//                // client oa application
//                .withClient("client1")
//                .secret(passwordEncoder.encode("client1_secret"))
//                .scopes("all")
//                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .redirectUris("http://client1.com/client1/login")
//                .accessTokenValiditySeconds(7200)
//                .autoApprove(true)
//
//                .and()
//
//                // client crm application
//                .withClient("client2")
//                .secret(passwordEncoder.encode("client2_secret"))
//                .scopes("all")
//                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .redirectUris("http://client2.com/client2/login")
//                .accessTokenValiditySeconds(7200)
//                .autoApprove(true)
//
//                .and()
//                .build();
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        endpoints.accessTokenConverter(jwtAccessTokenConverter())
////                .tokenStore(jwtTokenStore());
//        super.configure(endpoints);
//    }
//
////    @Bean
////    public JwtTokenStore jwtTokenStore() {
////        return new JwtTokenStore(jwtAccessTokenConverter());
////    }
////
////    @Bean
////    public JwtAccessTokenConverter jwtAccessTokenConverter() {
////        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
////        jwtAccessTokenConverter.setSigningKey("123456");
////        return jwtAccessTokenConverter;
////    }
//
//}
