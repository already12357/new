package com.zhq.frame.MyBatis;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Mybatis 的基本配置类
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
    @Resource
    public DataSource dataSource;

    /**
     * 注入 Mybatis 连接工厂, 用于获得 mybatis 的数据库链接
     * @return
     */
//    @Bean(name = "sqlSessionFactory")
//    SqlSessionFactory sqlSessionFactory() {
//        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build();
//
//
//        // 用于加载资源文件的解析类
//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage("com.qiyu.admin.*.model");
//
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//
//        //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});
//
//        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            bean.setMapperLocations(resolver.getResources("classpath*:mapper/*Mapper.xml"));
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

    @Bean
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
