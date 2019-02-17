package com.niocoder.aop.aspectj;

import com.niocoder.aop.Advice;
import com.niocoder.aop.Pointcut;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AspectJBeforeAdvice implements Advice {

    // 通知的前置方法  即 TransactionManager.start()
    private Method adviceMethod;
    // 判该对象中的方法是否符合pointcut
    private AspectJExpressionPointcut pointcut;
    // 通知的对象 即 TransactionManager
    private Object adviceObject;

    public AspectJBeforeAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }

    /**
     * 执行通知方法，再执行被代理类方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        this.invokeAdviceMethod();
        Object o = invocation.proceed();
        return o;
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }

    /**
     * 执行通知方法  TransactionManager.start()
     *
     * @throws Throwable
     */
    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObject);
    }
}
