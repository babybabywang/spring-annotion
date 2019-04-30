package com.study.config;

import com.study.aop.LogAspects;
import com.study.aop.MathCallculator;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP[动态代理]
 *      指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行编程方式；
 *      告诉Spring那个类是切面类，使用@Aspect注解
 *      @EnableAspectJAutoProxy开启AOP
 *              在Spring很多的@EnableXXX;功能
 *
 * 基于SpringAop使用的三步
 * 1)将业务逻辑组件和切面类都加入容器中，告诉Spring那个是切面类@Aspect注解
 * 2)在切面类的每一个通知方法标注通知注解，告诉Spring何时何地运行(切入点表达式)
 * 3) @EnableAspectJAutoProxy开启AOP
 *
 * AOP原理:[看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么]
 *
 *  总结:
 *      1）、 @EnableAspectJAutoProxy开启AOP功能
 *      2）、 @EnableAspectJAutoProxy会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *      3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *      4）、容器的创建流程：
 *          1）、registerBeanPostProcessors()注册后置处理器；创建
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAop {
    @Bean
    public MathCallculator mathCallculator(){
        return new MathCallculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

    @Test
    public void test(){
        AnnotationConfigApplicationContext ant = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        MathCallculator bean = ant.getBean(MathCallculator.class);
        bean.div(1,1);
        //bean.div(1,0);
    }
}
