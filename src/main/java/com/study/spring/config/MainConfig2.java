package com.study.spring.config;

import com.study.spring.bean.ColorFactoryBean;
import com.study.spring.bean.Person;
import com.study.spring.condition.LinuxCondition;
import com.study.spring.condition.WindowCondition;
import com.study.spring.importselector.MyImportSelector;
import org.springframework.context.annotation.*;

@Configuration
@Import(value = { MyImportSelector.class})
public class MainConfig2 {
    /**
     * 将Scope设置为prototype模式后，spring只负责对象的创建，不负责监控对象的生命周期，
     * 默认懒加载，每次获取到的bean都不同
     * 默认singleton的情况下，IOC启动会创建对象放入容器中，以后每次获取会直接冲容器中拿，而且SpringIoc容器会监控
     * 这个bean的全部生命周期,可以使用注解@Lazy使得singleton实例bean在获取对象时在创建对象
     *
     * @return
     */
    @Bean
    //@Scope(value = "prototype")
    @Lazy
    public Person person() {
        System.out.println("给容器添加发那个发");
        return new Person();
    }

    /**
     * @Conditional:按照一定的条件进行判断，满足条件给容器注册bean
     */
    @Bean
    @Conditional({WindowCondition.class})
    public Person person1() {
        return new Person("hsm1", 23);
    }

    @Bean
    @Conditional({LinuxCondition.class})
    public Person person2() {
        return new Person("hsm2", 23);
    }

    /**
     * 给SpringIOC容器快速注册组件
     * 1)扫描+包注解(@Controller/@Service/@Repository/@Component 包扫描:ComponentScan) 局限于自己写的类
     * 2)@Bean 导入第三方包里的组件
     * 3)@Import 快速的给容器中导入一个组件
     *          1)@Import(Class.class) id是全类名
     *          2)@ImportSelector
     *          3)@ImportBeanDefinitionRegistrar
     * 4）使用Spring提供的FactoryBean
     */
    @Bean
    public ColorFactoryBean factoryBean() {
        return new ColorFactoryBean();
    }
}
