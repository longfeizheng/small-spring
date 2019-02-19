package com.niocoder.beans.factory;

import com.niocoder.beans.BeansException;

/**
 * @author zhenglongfei
 */
public interface BeanFactoryAware {

    /**
     * set beanFactory
     *
     * @param beanFactory
     * @throws BeansException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
