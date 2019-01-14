package com.niocoder.beans.factory.support;


import com.niocoder.beans.BeanDefinition;

/**
 * BeanDefinition 实现类
 *
 * @author zhenglongfei
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }
}
