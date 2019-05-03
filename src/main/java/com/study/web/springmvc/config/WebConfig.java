package com.study.web.springmvc.config;

import com.study.web.springmvc.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Web配置只负责来扫描控制器、视图解析器、处理器映射器 子容器
 * 只扫描Controller
 * useDefaultFilters = false禁用默认的过滤规则，否则就不能生效
 * 定制化配置SpringMVC的拦截器、视图解析器等
 *
 * @author
 */
@ComponentScan(includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
}, useDefaultFilters = false)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 定制视图解析器
     *
     * @param registry
     */
    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从WEN-INF下找
        registry.jsp("/WEB-INF/", ".jsp");
        //配置freeMarker
        //registry.freeMarker().prefix("/").suffix(".html");
    }

    /**
     * 将SpringMVC的处理不了的请求交给tomcat；如静态资源的访问
     *
     * @param configurer
     */
    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //开启对静态资源的访问相当于
        configurer.enable();
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .order(1);
    }
}
