package com.niocoder.aop.framework;

/**
 * Created on 2019/2/17.
 * <p>
 * 获取代理类工厂
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AopProxyFactory {

    /**
     * 获取代理类
     *
     * @return
     */
    Object getProxy();

    /**
     * 根据类加载器获取代理类
     *
     * @param classLoader
     * @return
     */
    Object getProxy(ClassLoader classLoader);
}
