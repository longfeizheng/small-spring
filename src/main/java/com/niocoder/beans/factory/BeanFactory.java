package com.niocoder.beans.factory;

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
     */
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
}
