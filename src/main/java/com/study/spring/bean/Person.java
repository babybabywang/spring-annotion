package com.study.spring.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.annotation.PostConstruct;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    @Value("huangsm")
    private String name;
    @Value("#{20-1}")
    private Integer age;

    /**
     * 在对象实例化之间
     */
    //@PostConstruct
    public void init(){
        this.name="黄";
        this.age=11;
    }

    private void destory() {
        System.out.println("我被销毁了");
    }

    private void initPerson() {
        System.out.println(this.name+"初始化了");
    }
}
