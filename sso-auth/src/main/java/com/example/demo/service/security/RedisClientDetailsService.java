package com.example.demo.service.security;

import com.example.demo.constant.CacheConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 自定义数据库读取oauth数据
 */
public class RedisClientDetailsService extends JdbcClientDetailsService {

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 以clientId作key，缓存数据
     *
     * @param clientId
     * @return
     * @throws InvalidClientException
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
