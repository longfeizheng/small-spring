package com.niocoder.aop;

/**
 * Created on 2019/2/16.
 * <p>
 * 切点配置
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface Pointcut {

    /**
     * 获取MethodMatcher 判断方法时候匹配
     *
     * @return
     */
    MethodMatcher getMethodMatcher();

    /**
     * 获取expression表达式
     *
     * @return
     */
    String getExpression();
}
