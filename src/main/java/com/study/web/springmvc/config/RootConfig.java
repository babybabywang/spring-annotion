package com.study.web.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 根配置类，负责业务逻辑组件、数据源、持久化层等
 * Spring的容器不扫描Controller
 * @author huangsm
 */
@ComponentScan(value = "com.study", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
public class RootConfig {
}
