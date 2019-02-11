package com.niocoder.beans.factory.support;


import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.ConstructorArgument;
import com.niocoder.beans.SimpleTypeConverter;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.util.ClassUtils;
import lombok.extern.java.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 将<constructor-arg ref="accountDao"/>
 * accountDao 转换成实例bean
 *
 * @author zhenglongfei
 */
@Log
public class ConstructorResolver {

    private final DefaultBeanFactory factory;

    public ConstructorResolver(DefaultBeanFactory factory) {
        this.factory = factory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;

        Class beanClass = null;

        if (null == beanClass) {
            try {
                beanClass = ClassUtils.getDefaultClassLoader().loadClass(bd.getBeanClassName());
                bd.setBeanClass(beanClass);
            } catch (ClassNotFoundException e) {
                throw new BeanCreationException(bd.getId(), "Instantiation of bean failed, can't resolve class", e);
            }
        }
        Constructor[] candidates = beanClass.getConstructors();

        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.factory);

        ConstructorArgument cargs = bd.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        for (int i = 0; i < candidates.length; i++) {
            Class[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            boolean result = this.valuesMatchTypes(parameterTypes, cargs.getArgumentValues(), argsToUse, valueResolver, typeConverter);
            if (result) {
                constructorToUse = candidates[i];
                break;
            }
        }

        //找不到一个合适的构造函数
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getId(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getId(), "can't find a create instance using " + constructorToUse);
        }
    }

    private boolean valuesMatchTypes(Class[] parameterTypes, List<ConstructorArgument.ValueHolder> argumentValues, Object[] argsToUse, BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {

        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = argumentValues.get(i);
            // 判断参数类型有可能是RuntimeBeanReference 也有可能是TypedStringValue
            Object originalValue = valueHolder.getValue();

            try {
                // 获取真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);

                // 专程对应的参数类型 如 "1" Integer
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                log.info(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
