package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author zhenglongfei
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    protected abstract Object createBean(BeanDefinition bd) throws BeanCreationException;
}
