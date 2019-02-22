package com.niocoder.aop.aspectj;

import com.niocoder.aop.Advice;
import com.niocoder.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * 提出来一个抽象类
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public abstract class AbstractAspectJAdvice implements Advice {

    private Method adviceMethod;
    private AspectJExpressionPointcut pointcut;
    private Object adviceObject;

    public AbstractAspectJAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public Method getAdviceMethod() {
        return this.adviceMethod;
    }

    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObject);
    }
}
