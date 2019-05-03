package com.study.spring.config;

import com.study.spring.bean.Person;
import com.study.spring.bean.Red;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Autowired 默认按照类型依赖注入，如果存在多个相同类型的Bean会按照默认BeanId注入
 * @Qualifier("bookDao"):使用@Qualifier指定需要装配的组件的id，而不是使用属性名
 * @Autowired(required=false); 找到了自动装配，没有找到就不装配。
 * @Primary 让Spring进行自动装配的时候，默认使用首选的Bean
 * 也可以继续使用@Qualifier指定到底装配那个Bean
 * @Resource（JSR250）和@Inject(JSR330)[java规范的注解]
 * @Resource: 可以和@Autowired一样实现自动装配功能；默认是按照属性名称完成装配；
 * 没有能支持@Primary功能没有支持@Autowired(required=false)；
 * @Inject: 需要导入javax.inject的包，和 @Autowired的功能一样。但是不支持找到了自动装配，没有找到就不装配。
 * AutowiredAnnotationBeanPostProcessor解析自动装配
 * @Autowired:方法、属性、构造器、参数；都是从容器中获取参数组件的值 1.标注在方法上，spring容器创建当前对象时会调用该方法完成赋值。方法使用的参数，自定义类型的值从IOC容器中获取
 * 2.默认加载IOC容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作，标注在构造器上默认执行加注解的构造器初始化
 * 3.标注在参数位置
 *
 * 自定义组件使用Spring容器底层的一些组件（ApplicationContext、BeanFactory，xx);
 * 自定义组件显示XXXAware接口；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware
 * 将Spring底层的组件注入到自定义Bean中
 */
@Configuration
public class MainConfigOfPropertyValues {
    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Red red() {
        return new Red();
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext ant = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        Red bean = ant.getBean(Red.class);
        System.out.println(bean);
    }
}
