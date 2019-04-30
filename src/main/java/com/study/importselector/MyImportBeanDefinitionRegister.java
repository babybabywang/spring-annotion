package com.study.importselector;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param annotationMetadata 当前类的注解
     * @param beanDefinitionRegistry  beanDefinition的注册类
     *                      把所有需要注册的bean添加到容器中
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

    }
}
