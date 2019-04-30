package com.study.config;

import com.study.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * @author Administrator
 */
@Configuration
@ComponentScan(value = "com.study")
public class MainConfig {

    @Bean(initMethod = "initPerson",destroyMethod = "destory")
    public Person getPerson(){
        return new Person();
    }

}

