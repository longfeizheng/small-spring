package com.niocoder.aop.framework;

import com.niocoder.aop.Advice;
import com.niocoder.util.Assert;
import com.niocoder.util.ClassUtils;
import lombok.extern.java.Log;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenglongfei
 */
@Log
public class JdkAopProxyFactory implements AopProxyFactory, InvocationHandler {

    private final AopConfig config;

    public JdkAopProxyFactory(AopConfig config) throws AopConfigException {
        Assert.notNull(config, "AdvisedSupport must not be null");
        if (config.getAdvices().size() == 0) {
            throw new AopConfigException("No advices specified");
        }
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(ClassUtils.getDefaultClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {

        log.info("Creating JDK dynamic proxy: target source is " + this.config.getTargetObject());

        Class<?>[] proxiedInterfaces = config.getProxiedInterfaces();

        return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object target = this.config.getTargetObject();
        Object retVal;
        List<Advice> chain = this.config.getAdvices(method);
        if (chain.isEmpty()) {
            retVal = method.invoke(target, args);
        } else {
            List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
            interceptors.addAll(chain);
            // We need to create a method invocation...
            retVal = new ReflectiveMethodInvocation(target, method, args, interceptors).proceed();
        }

        return retVal;
    }
}
