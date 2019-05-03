package com.study.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器:对bean进行后置处理
 *
 * @author huangsm
 */
@Component
public class Test1 implements BeanPostProcessor{
    /**
     * 后置处理器前置初始化
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("我被初始化了" + beanName + "=>" + bean);
        return bean;
    }

    /**
     * 后置处理器后置初始化
     * @param bean bean实力
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("我被后置初始化了" + beanName + "=>" + bean);
        return bean;
    }
}
