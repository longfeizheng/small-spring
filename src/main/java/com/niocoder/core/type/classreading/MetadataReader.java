package com.niocoder.core.type.classreading;

import com.niocoder.core.io.Resource;
import com.niocoder.core.type.AnnotationMetadata;
import com.niocoder.core.type.ClassMetadata;

/**
 * 封装 ClassMetadataReadingVisitor和AnnotationMetadataReadingVisitor
 *
 * @author zhenglongfei
 */
public interface MetadataReader {

    /**
     * 获取资源信息
     *
     * @return
     */
    Resource getResource();

    /**
     * 获取类信息
     *
     * @return
     */
    ClassMetadata getClassMetadata();

    /**
     * 获取注解信息
     *
     * @return
     */
    AnnotationMetadata getAnnotationMetadata();
}
