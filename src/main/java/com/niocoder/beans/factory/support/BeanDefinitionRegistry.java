package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;

/**
 * 注册BeanDefinition接口
 *
 * @author zhenglongfei
 */
public interface BeanDefinitionRegistry {

    /**
     * 获取beanDefinition
     *
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 注册beanDefinition
     *
     * @param beanId
     * @param bd
     */
    void registerBeanDefinition(String beanId, BeanDefinition bd);
}
