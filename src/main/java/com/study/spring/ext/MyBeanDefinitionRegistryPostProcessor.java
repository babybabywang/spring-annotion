package com.study.spring.ext;

import com.study.spring.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义BeanDefinitionRegistryPostProcessor
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    /**
     * bean定义信息的存储中心，以后BeanFactory就是按照BeanDefinitionRegistry里面保存一个bean的定义信息
     *
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry。。。。。。bean的数量" + registry.getBeanDefinitionCount());
        //想BeanFactory注册bean
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class);
        registry.registerBeanDefinition("hello", beanDefinition.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor。。。。。。bean的数量" + beanFactory.getBeanDefinitionCount());
    }
}
