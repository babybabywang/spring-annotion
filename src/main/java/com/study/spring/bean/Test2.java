package com.study.spring.bean;

/**
 * 通过注解方式
 */
public class Test2 {
    public Test2() {
        System.out.println("Test2");
    }

    public void init(){
        System.out.println("初始化了");
    }
    public void destory(){
        System.out.println("被销毁了");
    }
}
