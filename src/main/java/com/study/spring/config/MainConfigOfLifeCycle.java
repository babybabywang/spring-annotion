package com.study.spring.config;

import com.study.spring.bean.Test;
import com.study.spring.bean.Test2;
import com.study.spring.bean.Test3;
import org.springframework.context.annotation.*;

/**
 * bean的生命周期
 *          bean创建---初始化---销毁的过程
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法；  @Bean(initMethod = "init",destroyMethod = "destory")
 * 实现InitializingBean, DisposableBean 接口
 * 实现BeanPostProcessor bean后置处理器
 *  @PostConstruct  在对象创建并且赋值之后调用
 *  @PreDestroy 容器移除之前调用
 *
 *  Spring底层对BeanPostProcessor
 */
@Configuration
@ComponentScan("com.study.bean")
public class MainConfigOfLifeCycle {
    /**
     * 通过实现InitializingBean, DisposableBean接口
     * @return
     */
    @Bean
    public Test test(){
        return new Test();
    }

    @Scope("prototype")
    @Bean(initMethod = "init",destroyMethod = "destory")
    public Test2 test2(){
        return new Test2();
    }

    @Bean
    public Test3 test3(){
        return new Test3();
    }

    @org.junit.Test
    public void  test1(){
        AnnotationConfigApplicationContext ant = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
//        Test bean = ant.getBean(Test.class);
       //Test2 bean1 = ant.getBean(Test2.class);
        ant.close();
    }
}
