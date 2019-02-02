package com.niocoder.beans.factory.annotation;

import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.config.AutowireCapableBeanFactory;
import com.niocoder.beans.factory.config.DependencyDescriptor;
import com.niocoder.util.*;

import java.lang.reflect.Field;


public class AutowiredFieldElement extends InjectionElement {

    boolean required;

    public AutowiredFieldElement(Field f, boolean required, AutowireCapableBeanFactory factory) {
        super(f, factory);
        this.required = required;
    }

    public Field getField() {
        return (Field) this.member;
    }

    @Override
    public void inject(Object target) {
        Field field = this.getField();

        try {
            DependencyDescriptor desc = new DependencyDescriptor(field, this.required);
            Object value = factory.resolveDependency(desc);
            if (value != null) {
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        } catch (Throwable e) {
            throw new BeanCreationException("could not autowire field " + field, e);
        }
    }
}
