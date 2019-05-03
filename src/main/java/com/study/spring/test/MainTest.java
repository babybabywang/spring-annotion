package com.study.spring.test;

import com.study.spring.bean.Person;
import com.study.spring.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 主类扫描
 * @author Administrator
 */
public class MainTest {
    /**
     * 测试ComponentScan注解
     */
    @Test
    public void test(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("IOC容器初始化完毕");
        Person bean = annotationConfigApplicationContext.getBean(Person.class);
        Person bean1 = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(bean);
        System.out.println(bean1);
        annotationConfigApplicationContext.close();
    }

    @Test
    public void test01(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println(annotationConfigApplicationContext.getEnvironment().getProperty("os.name"));
        String[] beanNames = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        Map<String, Person> beansOfType = annotationConfigApplicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

    /**
     * 测试@Import注解
     */
    @Test
    public void test02() throws Exception {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Object bean = annotationConfigApplicationContext.getBean("factoryBean");
        //获得工厂bean本身
        Object bean1 = annotationConfigApplicationContext.getBean("&factoryBean");
        System.out.println(bean.getClass());
        System.out.println(bean==bean1);
    }
}
