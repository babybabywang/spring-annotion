package com.study.web.springmvc;

import com.study.web.springmvc.config.RootConfig;
import com.study.web.springmvc.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web容器启动的时候创建对象；调用方法来初始化根容器以及前端控制器
 *
 * @author huangsm
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取根容器的配置类；(Spring配置文件)  父容器；
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类(SpringMVC配置文件) 子容器；
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        //  /:代表拦截所有请求(包括静态资源(xx.js、xx.png))，但是不拦截xx.jsp
        // /*:也代表拦截所有请求，同时也拦截xx.jsp
        return new String[]{"/"};
    }
}
