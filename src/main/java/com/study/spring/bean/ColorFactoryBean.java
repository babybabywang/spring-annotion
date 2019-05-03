package com.study.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建Spring定义的FactoryBean
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    //返回一个color对象，这个对象会添加到容器中
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean的getObject被调用");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    //是否单例？
    //false是prototype模式
    public boolean isSingleton() {
        return false;
    }
}
