package com.niocoder.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created on 2019/2/17.
 * <p>
 * 给定一个对象和方法和 若干拦截器，拦截器可以依次执行
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    private final Object targetObject; // nioCoderService;
    private final Method targetMethod; // placeOrder 方法
    private final Object[] arguments; // 方法所需要的参数，这里为空

    private final List<MethodInterceptor> interceptorList;

    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object targetObject, Method targetMethod, Object[] arguments, List<MethodInterceptor> interceptorList) {
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }

    @Override
    public Method getMethod() {
        return this.targetMethod;
    }

    @Override
    public Object[] getArguments() {
        return (this.arguments != null ? this.arguments : new Object[0]);
    }

    @Override
    public Object proceed() throws Throwable {
        //所有的拦截器已经调用完成
        if (this.currentInterceptorIndex == this.interceptorList.size() - 1) {
            return invokeJoinPoint();
        }

        this.currentInterceptorIndex++;

        MethodInterceptor interceptor =
                this.interceptorList.get(this.currentInterceptorIndex);
        return interceptor.invoke(this);
    }

    private Object invokeJoinPoint() throws Throwable {

        return this.targetMethod.invoke(this.targetObject, this.arguments);
    }

    @Override
    public Object getThis() {
        return this.targetObject;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.targetMethod;
    }
}
