package com.niocoder.aop.framework;

import com.niocoder.aop.Advice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created on 2019/2/17.
 * <p>
 * 获取代理类时用到的一些配置
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AopConfig {

    /**
     * 目标类
     *
     * @return
     */
    Class<?> getTargetClass();

    /**
     * 目标对象
     *
     * @return
     */
    Object getTargetObject();

    /**
     * 是否是代理类
     *
     * @return
     */
    boolean isProxyTargetClass();

    /**
     * 接口
     *
     * @return
     */
    Class<?>[] getProxiedInterfaces();

    boolean isInterfaceProxied(Class<?> intf);

    /**
     * 获取通知列表
     *
     * @return
     */
    List<Advice> getAdvices();

    /**
     * 添加通知
     *
     * @param advice
     */
    void addAdvice(Advice advice);

    /**
     * 根据method 返回匹配method的通知
     *
     * @param method
     * @return
     */
    List<Advice> getAdvices(Method method/*,Class<?> targetClass*/);

    /**
     * 设置目标对象
     *
     * @param object
     */
    void setTargetObject(Object object);
}
