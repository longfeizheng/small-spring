package com.niocoder.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice {

    public AspectJAfterReturningAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        super(adviceMethod, pointcut, adviceObject);
    }

    /**
     * 先执行被代理类方法，再执行通知方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object o = invocation.proceed();
        this.invokeAdviceMethod();
        return o;
    }

}
