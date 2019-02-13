package com.niocoder.beans.factory.annotation;

import com.niocoder.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

/**
 * @author zhenglongfei
 */
public abstract class InjectionElement {

    protected Member member;
    protected AutowireCapableBeanFactory factory;

    InjectionElement(Member member, AutowireCapableBeanFactory factory) {
        this.member = member;
        this.factory = factory;
    }

    /**
     * 属性注入的抽象方法
     *
     * @param target
     */
    public abstract void inject(Object target);
}
