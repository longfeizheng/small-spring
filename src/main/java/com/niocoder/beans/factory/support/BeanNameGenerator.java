package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;

/**
 * 生成扫描bean 名称的接口
 *
 * @author zhenglongfei
 */
public interface BeanNameGenerator {

    /**
     * 根据bean的定义生成bean的名称
     * @param definition
     * @param registry
     * @return
     */
    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
