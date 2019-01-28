package com.niocoder.core.annotation;

import com.niocoder.util.Assert;

import java.util.LinkedHashMap;

import static java.lang.String.format;

/**
 * 扩展LinkedHashMap 记录注解信息
 *
 * @author zhenglongfei
 */
public class AnnotationAttributes extends LinkedHashMap<String, Object> {

    public AnnotationAttributes() {
    }

    public String getString(String attributeName) {
        return doGet(attributeName, String.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T doGet(String attributeName, Class<T> expectedType) {

        Object value = this.get(attributeName);
        Assert.notNull(value, format("Attribute '%s' not found", attributeName));
        return (T) value;
    }
}
