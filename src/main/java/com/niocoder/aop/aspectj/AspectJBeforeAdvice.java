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
public class AspectJBeforeAdvice extends AbstractAspectJAdvice {

    public AspectJBeforeAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        super(adviceMethod, pointcut, adviceObject);
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

}
