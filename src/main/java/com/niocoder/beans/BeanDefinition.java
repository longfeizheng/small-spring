package com.niocoder.beans;

import java.util.List;

/**
 * bean.xml bean的定义
 *
 * @author zhenglongfei
 */
public interface BeanDefinition {

    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";
    /**
     * 多例
     */
    String SCOPE_PROTOTYPE = "prototype";
    /**
     * 默认为空即单例模式
     */
    String SCOPE_DEFAULT = "";

    /**
     * 是否为单例
     *
     * @return
     */
    boolean isSingleton();

    /**
     * 是否为多例
     *
     * @return
     */
    boolean isPrototype();

    /**
     * 获取scope配置
     *
     * @return
     */
    String getScope();

    /**
     * 设置scope
     *
     * @param scope
     */
    void setScope(String scope);

    /**
     * 获取bean.xml中 bean的全名 如 "com.niocoder.service.v1.NioCoderService"
     *
     * @return
     */
    String getBeanClassName();

    /**
     * 获取bean.xml 中的 property 标签内容 <property name ="accountDao" ref="accountDao"></property>
     *
     * @return
     */
    List<PropertyValue> getPropertyValues();

    /**
     * 获取bean.xml 中的 constructor-arg 标签的内容 <constructor-arg ref="accountDao"/>
     *
     * @return
     */
    ConstructorArgument getConstructorArgument();

    /**
     * 获取bean 的 id
     *
     * @return
     */
    String getId();

    /**
     * 判断bean是否有构造参数
     *
     * @return
     */
    boolean hasConstructorArgumentValues();

    /**
     * 获取beanClass
     *
     * @return
     */
    Class getBeanClass();

    /**
     * 设置beanClass
     */
    void setBeanClass(Class beanClass);
}
