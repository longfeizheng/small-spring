package com.niocoder.aop.config;

import com.niocoder.beans.BeansException;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.beans.factory.BeanFactoryAware;
import com.niocoder.util.StringUtils;

/**
 * @author zhenglongfei
 */
public class AspectInstanceFactory implements BeanFactoryAware {

    private String aspectBeanName;

    private BeanFactory beanFactory;

    public void setAspectBeanName(String aspectBeanName) {
        this.aspectBeanName = aspectBeanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        if (!StringUtils.hasText(this.aspectBeanName)) {
            throw new IllegalArgumentException(" 'aspectBeanName' is required");
        }
    }

    public Object getAspectInstance() throws Exception {
        return this.beanFactory.getBean(this.aspectBeanName);
    }
}
