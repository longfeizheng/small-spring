package com.niocoder.beans.factory;

import com.niocoder.beans.BeanDefinition;

/**
 * 创建bean的实例
 * @author zhenglongfei
 */
public interface BeanFactory {

    /**
     * 获取bean的定义
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 获取bean的实例
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
