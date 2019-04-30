package com.study.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 使用ApplicationContextAware保存IOC容器，全局上下文文件
 */
public class Red implements ApplicationContextAware , EmbeddedValueResolverAware {
    public ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //保存IOC容器
        this.applicationContext = applicationContext;
    }

    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        String s = stringValueResolver.resolveStringValue("#{10-2}${os.name}");
        System.out.println(s);
    }
}
