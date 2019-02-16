package com.niocoder.aop;

import java.lang.reflect.Method;

/**
 * Created on 2019/2/16.
 * <p>
 * 方法匹配
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface MethodMatcher {

    /**
     * 给定一个方法判断是否匹配
     *
     * @param method
     * @return
     */
    boolean matches(Method method /*,Class<?> targetClass*/);
}
