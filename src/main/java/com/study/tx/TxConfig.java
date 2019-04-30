package com.study.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事务：
 * 环境搭建：
 * 1.导入相关依赖
 * 数据源、数据库驱动、Spring-jdbc模块
 * 2.配置数据源、JdbcTemplate(Spring提供的简化数据库操作的工具)操作数据
 * 3.在方法上加@Transactional标识当前方法是一个事务方法
 * 4.@EnableTransactionManagement开启基于注解的事务管理功能；需要配置事务管理器
 */
@EnableTransactionManagement
@Configuration
@ComponentScan("com.study.tx")
public class TxConfig {
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("mysql");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/crm");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //Spring对Configuration类会特殊处理，给容器中加组件，多次调用只会给从容器中找组件
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException {
        //事务管理器管理数据源
        return new DataSourceTransactionManager(dataSource());
    }
    @Test
    public void testTx(){
        AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = anno.getBean(UserService.class);
        userService.insertUser();
    }
}
