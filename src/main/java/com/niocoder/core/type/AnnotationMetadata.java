package com.niocoder.core.type;

import com.niocoder.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * 封装获取接口信息的接口
 *
 * @author zhenglongfei
 */
public interface AnnotationMetadata extends ClassMetadata {

    /**
     * 获取注解信息
     *
     * @return
     */
    Set<String> getAnnotationTypes();

    /**
     * 判断是否有该注解信息
     *
     * @param annotationType
     * @return
     */
    boolean hasAnnotation(String annotationType);

    /**
     * 获取注解的内容信息
     *
     * @param annotationType
     * @return
     */
    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
