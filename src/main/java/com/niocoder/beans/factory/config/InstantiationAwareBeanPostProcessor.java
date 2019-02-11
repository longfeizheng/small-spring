package com.niocoder.beans.factory.config;

import com.niocoder.beans.BeansException;

/**
 * 实例化的后处理器
 *
 * @author zhenglongfei
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 实例化之前
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 实例化之后
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * @param bean
     * @param beanName
     * @throws BeansException
     */
    default void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
    }

}
