package com.study.spring.ext;

import com.study.spring.bean.Red;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.*;

/**
 * 扩展原理:
 * BeanPostProcessor:bean后置处理器，bean创建对象初始化前后拦截工作的
 * BeanFactoryPostProcessor:beanFactory的后置处理器；在BeanFactory标准初始化之后调用，
 * 所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建
 * <p>
 * 1)、ioc创建对象
 * 2）、执行invokeBeanFactoryPostProcessor(beanFactory)方法；执行BeanFactoryPostProcessor；
 * 如何找到所有的BeanFactoryPostProcessor并执行他们的方法:
 * 1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 * 2)、在初始化创建其他组件前面执行
 * <p>
 * 2、接口BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
 * 在所有bena定义信息将要被加载，bean实例还未被创建的时候
 * 优先于BeanFactoryPostProcessor执行:
 * BeanDefinitionRegistryPostProcessor给容器中添加一些组件
 * 原理：
 * 1）、创建IOC容器
 * 2）、refresh()--》invokeBeanFactoryPostProcessor(beanFactory);
 * 3)、先从容器中获取所有的BeanDefinitionRegistryPostProcessor组件。
 * 1、依次触发所有的postProcessBeanDefinitionRegistry方法
 * 2、再来触发postProcessBeanFactory()方法BeanFactoryPostProcessor；
 * 4）、再来从容器中找到 BeanFactoryPostProcessor组件，然后在依次执行postProcessBeanFactory()方法
 * <p>
 * <p>
 * 3、ApplicationListener：监听容器中发布的事件。完成事件模型驱动开发；
 *
 * @FunctionalInterface public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 * void onApplicationEvent(E event);
 *
 * 自定义监听事件步骤:
 *          1)、写一个监听器来监听某个事件(ApplicationEvent及其子类)
 *          2)、把监听器加入到容器
 *          3)、只要容器中有相关事件的发布，就可以监听到事件
 *              ContextRefreshedEvent：容器刷新完成(所有bean都完全创建)会发布这个事件
 *              ContextClosedEvent:关闭容器会发布这个事件
 *          4）、发布一个事件
 *                anon.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
 *                     });
 *
 * 原理:
 *      （1）ContextRefreshedEvent事件
 *             1.容器创建对象的时候refresh()
 *             2.finishRefresh()容器刷新完成
 *             3.publishEvent(new ContextRefreshedEvent(this))
 *                  事件发布流程:
 *                      1）获取事件的多播器(派发器)	getApplicationEventMulticaster()
 *                      2)multicastEvent(applicationEvent, eventType);派发事件
 *                      3)获取到所有的ApplicationListener
 *                              for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *                              1)如果有Executor可以支持使用Executor进行异步派发
 *                                  	Executor executor = getTaskExecutor();
 *                              2）否则，直接执行invokeListener(listener, event);
 *                                  然后调用doInvokerListener方法，在回调listener.onApplicationEvent(event);方法
 *       （2）自己发布事件
 *       （3）容器关闭事件
 *
 *   [事件多播器(牌坊器)]applicationEventMulticaster
 *      1.容器创建对象:refresh()；
 *      2.调用initApplicationEventMulticaster()；初始applicationEventMulticaster
 *          1)、先去容器中找有没有id="applicationEventMulticaster"的组件
 *              如果有就冲BeanFactory中获取	beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
 *              如果没有就在new一个 this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);并且加入到容器中
 *              beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
 *
 * [容器中那些监听器]
 *          1.容器创建对象:refresh()；
 *          2.registerListeners()注册所有监听器
 *             从容器中拿到所有的监听器，把他们注册到getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *             String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 */
@Configuration
@ComponentScan("com.study.ext")
public class ExtConfig {

    @Bean
    public Red red() {
        return new Red();
    }

    @Test
    public void testBeanFactory() {
        AnnotationConfigApplicationContext anon = new AnnotationConfigApplicationContext(ExtConfig.class);
        //自定义发布事件
        anon.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
        });
        anon.close();
    }
}
