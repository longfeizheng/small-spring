package com.niocoder.beans.factory;

import com.niocoder.beans.BeansException;

/**
 * 获取beanDefinition时的异常类
 * @author zhenglongfei
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanDefinitionStoreException(String s) {
        super(s);
    }
}
