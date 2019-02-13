package com.niocoder.beans.factory.config;

import java.lang.reflect.Field;

/**
 * 表明属性注入或者方法注入
 *
 * @author zhenglongfei
 */
public class DependencyDescriptor {
    /**
     * 属性注入
     */
    private Field field;
    /**
     * 方法注入
     */
//    private MethodParameter methodParameter;

    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        this.field = field;
        this.required = required;
    }

    public Class<?> getDependencyType() {
        if (this.field != null) {
            // 字段的类型如 AccountDao ItemDao
            return field.getType();
        }
        // TODO 方法注入不支持
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }
}
