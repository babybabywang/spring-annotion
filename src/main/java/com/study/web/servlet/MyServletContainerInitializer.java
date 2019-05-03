package com.study.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 使用Shared libraries（共享库） / runtimes pluggability（运行时插件能力）
 * 容器启动的时候会将@HandlesTypes指定的这个类型下面的资料(子接口，实现类等)传递过来
 * 传入感兴趣的类型
 * @author huangsm
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     * 应用启东时，会运行onStartup方法
     *
     * @param set            感兴趣的类型所有的子类型，这里是HelloService的子类
     * @param servletContext 代表当前Web应用的ServletContext，一个web一个ServletContext
     * @throws ServletException
     *
     * 1)、使用ServletContext注册Web组件(Listener、Servlet、Filter)
     * 2)、使用编码方式，在项目启动的时候给ServletContext中添加组件
     *          必须在项目启动的时候(否则无法给ServletContext中添加组件)
     *          1)、ServletContainerInitializer得到servletContext
     *          2）、通过ServletContextListener监听器给ServletContext中添加组件
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        set.forEach(System.out::println);
        //注册Servlet
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("userServlet", new UserServlet());
        //配置Servlet的url映射
        dynamic.addMapping("user");
        //注册拦截器
        servletContext.addListener(UserListener.class);
        //注册过滤器
        FilterRegistration.Dynamic filter = servletContext.addFilter("userFilter", UserFilter.class);
        //配置过滤器的映射信息
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        System.out.println("默认启动了~");
    }
}
