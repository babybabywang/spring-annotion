package com.study.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 通过SPI机制在容器启动时注册监听器
 */
public class UserListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听ServletContext的销毁");
    }
}
