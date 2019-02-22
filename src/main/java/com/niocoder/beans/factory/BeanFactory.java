package com.niocoder.beans.factory;

import java.util.List;

/**
 * 创建bean的实例
 *
 * @author zhenglongfei
 */
public interface BeanFactory {

    /**
     * 获取bean的实例
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);

    /**
     * 根据bean 名称 返回 class 对象
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;

    /**
     * 根据 class 类型 返回所有实例
     *
     * @param type
     * @return
     */
    List<Object> getBeansByType(Class<?> type);
}
