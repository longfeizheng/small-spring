package com.niocoder.beans.factory.config;

import com.niocoder.beans.BeansException;

/**
 * bean的后置处理器
 *
 * @author zhenglongfei
 */
public interface BeanPostProcessor {

    /**
     * 初始化之前的操作
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 初始化之后的操作
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
