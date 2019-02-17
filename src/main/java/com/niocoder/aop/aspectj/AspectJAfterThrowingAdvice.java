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
public class AspectJAfterThrowingAdvice implements Advice {

    // 通知的前置方法  即 TransactionManager.rollback()
    private Method adviceMethod;
    // 判该对象中的方法是否符合pointcut
    private AspectJExpressionPointcut pointcut;
    // 通知的对象 即 TransactionManager
    private Object adviceObject;

    public AspectJAfterThrowingAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }

    /**
     * 先执行被代理类方法，再执行通知方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable t) {
            invokeAdviceMethod();
            throw t;
        }
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }

    /**
     * 执行通知方法  TransactionManager.commit()
     *
     * @throws Throwable
     */
    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObject);
    }
}
