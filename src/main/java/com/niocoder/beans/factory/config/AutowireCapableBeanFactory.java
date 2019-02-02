package com.niocoder.beans.factory.config;

import com.niocoder.beans.factory.BeanFactory;

/**
 * 表明注解 注入的beanFactory
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 根据字段属性的描述，获得所对应的实例
     *
     * @param descriptor
     * @return
     */
    Object resolveDependency(DependencyDescriptor descriptor);
}
