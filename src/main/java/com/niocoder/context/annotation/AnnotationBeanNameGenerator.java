package com.niocoder.context.annotation;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.support.BeanDefinitionRegistry;
import com.niocoder.beans.factory.support.BeanNameGenerator;

/**
 * 实现BeanNameGenerator
 *
 * @author zhenglongfei
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return null;
    }
}
