package com.study.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet3.0 异步请求
 *
 * @author huangsm
 */
@WebServlet(value = "/async", asyncSupported = true)
public class AysncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("主线程开始" + Thread.currentThread());
        //1.支持异步处理,asyncSupported = true
        //2.开启异步处理
        AsyncContext startAsync = req.startAsync();
        //3.开始异步处理
        startAsync.start(() -> {
            System.out.println("副线程开始" + Thread.currentThread());
            try {
                sayHello();
                //异步调用完毕
                startAsync.complete();
                //获取到异步的上下文
                AsyncContext asyncContext = req.getAsyncContext();
                //4.获取响应
                ServletResponse response = asyncContext.getResponse();
                response.getWriter().write("hello async ...");
            } catch (InterruptedException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("副线程结束" + Thread.currentThread());
        });
        System.out.println("主线程结束" + Thread.currentThread());

    }

    public void sayHello() throws InterruptedException {
        Thread.sleep(3000);
    }
}
