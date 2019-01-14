package com.niocoder.beans;

/**
 * bean.xml bean的定义
 * @author zhenglongfei
 */
public interface BeanDefinition {

    /**
     * 获取bean.xml中 bean的全名 如 "com.niocoder.service.v1.NioCoderService"
     * @return
     */
    String getBeanClassName();
}
