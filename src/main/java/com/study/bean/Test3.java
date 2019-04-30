package com.study.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Test3 {
    public Test3() {
        System.out.println("test3");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化下");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁下");
    }
}
