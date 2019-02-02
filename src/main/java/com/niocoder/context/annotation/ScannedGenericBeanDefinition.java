package com.niocoder.context.annotation;

import com.niocoder.beans.factory.annotation.AnnotatedBeanDefinition;
import com.niocoder.beans.factory.support.GenericBeanDefinition;
import com.niocoder.core.type.AnnotationMetadata;

/**
 * 实现 AnnotatedBeanDefinition 接口
 *
 * @author zhenglongfei
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        this.metadata = metadata;
        setBeanClassName(this.metadata.getClassName());
    }

    @Override
    public AnnotationMetadata getMetadata() {
        return metadata;
    }
}
