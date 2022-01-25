package com.example.demo.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = MybatisSystemConfig.SCAN_PACKAGE, sqlSessionFactoryRef = MybatisSystemConfig.BEAN_SQL_SESSION_FACTORY_NAME)
public class MybatisSystemConfig {

    /* 多数据原配置 - 修改开始 */
    static final String SCAN_PACKAGE = "com.example.demo.mapper.system";
    static final String MAPPER_LOCATION = "classpath*:mapper/system/*.xml";
    static final String DATA_SOURCE_PROPERTIES_PREFIX = "spring.datasource.system";
    static final String BEAN_NAME_PREFIX = "system";
    static final String TYPE_ALIASES_PACKAGE = "com.example.demo.mapper.system";
    /* 多数据原配置 - 修改结束 */

    static final String BEAN_SQL_SESSION_FACTORY_NAME = BEAN_NAME_PREFIX + "SqlSessionFactory";
    static final String BEAN_DATA_SOURCE_NAME = BEAN_NAME_PREFIX + "DataSource";
    static final String BEAN_TRANSACTION_MANAGER_NAME = BEAN_NAME_PREFIX + "TransactionManager";
    static final String BEAN_SQL_SESSION_TEMPLATE_NAME = BEAN_NAME_PREFIX + "SqlSessionTemplate";


    @Primary
    @Bean(name = BEAN_DATA_SOURCE_NAME)
    @ConfigurationProperties(prefix = DATA_SOURCE_PROPERTIES_PREFIX)
    public DruidDataSource dataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = BEAN_TRANSACTION_MANAGER_NAME)
    public DataSourceTransactionManager transactionManager(@Qualifier(value = BEAN_DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = BEAN_SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier(value = BEAN_DATA_SOURCE_NAME) DataSource dataSource
    ) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        //分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");           //方言
        properties.setProperty("rowBoundsWithCount", "true");       //使用 RowBounds 分页会进行 count 查询
        properties.setProperty("reasonable", "true");               //pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        properties.setProperty("pageSizeZero", "true");             //如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果
        properties.setProperty("supportMethodsArguments", "true");  //支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("offsetAsPageNum", "true");          //将 RowBounds 中的 offset 参数当成 pageNum 使用
        pageInterceptor.setProperties(properties);

        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});

        //设置mapper location
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MybatisSystemConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Primary
    @Bean(name = BEAN_SQL_SESSION_TEMPLATE_NAME)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = BEAN_SQL_SESSION_FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
