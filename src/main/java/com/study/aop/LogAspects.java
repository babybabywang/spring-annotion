package com.study.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 将切面类和目标方法所在的业务逻辑组件都放在容器中
 */
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     * 1.本类引用pointCut()
     * 2.其他切面切入 方法全名
     */
    @Pointcut("execution(public int com.study.aop.MathCallculator.div(int ,int )))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart() {
        System.out.println("除法开始");
    }

    /**
     * JoinPoint一定要出现在参数表的第一位
     */
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("除法结束" + Arrays.asList(joinPoint.getArgs()) + ":" + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法正常返回。。。运行结果｛" + result + "｝");
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void logException(Exception e) {
        System.out.println("除法出现异常。。。" + e);
    }

  /*  @Around("pointCut()")
    public void logAround(JoinPoint joinPoint){
        System.out.println("hehe ");
    }*/
}
