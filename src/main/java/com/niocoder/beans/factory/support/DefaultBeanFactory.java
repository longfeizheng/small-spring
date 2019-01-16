package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * BeanFactory的默认实现类
 *
 * @author zhenglongfei
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {


    /**
     * 存放BeanDefinition
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanId, bd);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if (bd == null) {
            throw new BeanCreationException("BeanDefinition does not exist");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();

        String beanClassName = bd.getBeanClassName();
        try {
            // 使用反射创建bean的实例，需要对象存在默认的无参构造方法
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
    }
}
