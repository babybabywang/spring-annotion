package com.study.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 实现接口，自定义bean的初始化和销毁方法，这个方法对代码的耦合度太高不支持
 */
public class Test implements InitializingBean, DisposableBean {
    public Test() {
        System.out.println("test");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("test:我被创建了");
    }

    public void destroy() throws Exception {
        System.out.println("test:我被销毁了");
    }
}
