package com.study.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowCondition implements Condition {
    /**
     *
     * @param conditionContext 判断条件能够使用的上下文环境
     * @param annotatedTypeMetadata 注解类型元数据
     * @return
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //是否Java语言
        //1.能获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();

        //2.获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();

        //3.获取环境
        Environment environment = conditionContext.getEnvironment();

        //获取bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String property = environment.getProperty("os.name");

        if (property.contains("Windows")){
            return true;
        }
        return false;
    }
}
