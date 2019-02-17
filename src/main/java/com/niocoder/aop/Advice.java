package com.niocoder.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface Advice extends MethodInterceptor {

    /**
     * 获取Pointcut
     * @return
     */
    Pointcut getPointcut();
}
