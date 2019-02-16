package com.niocoder.aop.config;

import com.niocoder.beans.BeanUtils;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created on 2019/2/16.
 * <p>
 * 根据 bean的名称和方法名称返回对应的Method
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class MethodLocatingFactory {

    private String targetBeanName;

    private String methodName;

    private Method method;

    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    /**
     * 设置beanFactory 只有beanFactory才能根据bean的名称返回bean的class 对象
     * 设置时需要前置判断，beanName 和 methodName
     *
     * @param beanFactory
     */
    public void setBeanFactory(BeanFactory beanFactory) {

        if (!StringUtils.hasText(this.targetBeanName)) {
            throw new IllegalArgumentException("Property 'targetBeanName' is required");
        }
        if (!StringUtils.hasText(this.methodName)) {
            throw new IllegalArgumentException("Property 'methodName' is required");
        }

        Class<?> beanClass = beanFactory.getType(this.targetBeanName);
        if (beanClass == null) {
            throw new IllegalArgumentException("Can't determine type of bean with name '" + this.targetBeanName + "'");
        }

        // 给method 赋值
        this.method = BeanUtils.resolveSignature(this.methodName, beanClass);

        if (this.method == null) {
            throw new IllegalArgumentException("Unable to locate method [" + this.methodName +
                    "] on bean [" + this.targetBeanName + "]");
        }
    }

    /**
     * 返回Method对象
     *
     * @return
     */
    public Method getObject() {
        return this.method;
    }
}
