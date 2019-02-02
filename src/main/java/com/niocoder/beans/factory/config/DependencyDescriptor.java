package com.niocoder.beans.factory.config;

import java.lang.reflect.Field;

/**
 * 表明注解注入中的字段注入
 */
public class DependencyDescriptor {

    private Field field;
    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        this.field = field;
        this.required = required;
    }

    public Class<?> getDependencyType() {
        if (this.field != null) {
            return field.getType();// 字段的类型如 AccountDao ItemDao
        }
        // TODO 构造器注入
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }
}
