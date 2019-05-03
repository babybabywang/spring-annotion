package com.study.web.springmvc.controller;

import com.study.web.springmvc.service.DeferredResultQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * SpringMVC的异步处理
 *
 * @author huangsm
 */
@RestController
public class AsyncController {


    @GetMapping("/createOrder")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> deferredResult = new DeferredResult<>(3000L, "create fail");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }

    @GetMapping("/create")
    public String create() {
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResultQueue.get().setResult(order);
        return "success";
    }


    /**
     * 1、控制器方位Callable后；
     * 2、Spring异步处理，将Callable提交到TaskExecutor，使用一个隔离的线程进行执行；
     * 3、DispatcherServlet和所有的Filter退出web容器的线程，但是response保持打开状态；
     * 4、Callable返回结果SpringMVC将请求重新派发给Servlet容器，恢复之前的处理；
     * 5、根据Callable返回的结果。SpringMVC继续进行视图渲染流程等(从收请求-视图渲染)
     * <p>
     * 异步的拦截器:
     * 1)原生Api的AsyncListener
     * 2)SpringMVC:实现AsyncHandlerInterceptor
     *
     * @return
     */
    @GetMapping("asyncHello")
    public Callable<String> asyncHello() {
        return () -> "Callable<String> asyncHello";
    }
}
