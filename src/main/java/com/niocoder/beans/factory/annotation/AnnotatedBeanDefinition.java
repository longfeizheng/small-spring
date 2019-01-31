package com.niocoder.beans.factory.annotation;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.core.type.AnnotationMetadata;

/**
 * 注解的BeanDefinition接口
 *
 * @author zhenglongfei
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {

    /**
     * 获取注解信息
     *
     * @return
     */
    AnnotationMetadata getMetadata();
}
