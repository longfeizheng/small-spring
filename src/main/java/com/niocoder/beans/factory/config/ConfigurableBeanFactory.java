package com.niocoder.beans.factory.config;

import java.util.List;

/**
 * 可配置的beanFactory
 *
 * @author zhenglongfei
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {

    /**
     * 增加BeanPostProcessor
     *
     * @param postProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor postProcessor);

    /**
     * 获取 BeanPostProcessor
     *
     * @return
     */
    List<BeanPostProcessor> getBeanPostProcessors();
}
