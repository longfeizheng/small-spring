package com.niocoder.test.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanInfoUtil {

    /**
     * @param userInfo 实例
     * @param propertyName 属性名
     * @throws Exception
     */
    public static void setProperty(UserInfo userInfo, String propertyName) throws Exception {
        PropertyDescriptor propDesc = new PropertyDescriptor(propertyName, UserInfo.class);
        Method methodSetUserName = propDesc.getWriteMethod();
        methodSetUserName.invoke(userInfo, "郑龙飞");
        System.out.println("set userName:" + userInfo.getUserName());
    }

    /**
     * @param userInfo 实例
     * @param propertyName 属性名
     * @throws Exception
     */
    public static void getProperty(UserInfo userInfo, String propertyName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(propertyName, UserInfo.class);
        Method methodGetUserName = proDescriptor.getReadMethod();
        Object objUserName = methodGetUserName.invoke(userInfo);
        System.out.println("get userName:" + objUserName.toString());
    }

    /**
     * @param userInfo 实例
     * @param propertyName 属性名
     * @throws Exception
     */
    public static void setPropertyByIntrospector(UserInfo userInfo, String propertyName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(propertyName)) {
                    Method methodSetUserName = propDesc.getWriteMethod();
                    methodSetUserName.invoke(userInfo, "niocoder");
                    System.out.println("set userName:" + userInfo.getUserName());
                    break;
                }
            }
        }
    }

    /**
     * @param userInfo 实例
     * @param propertyName 属性名
     * @throws Exception
     */
    public static void getPropertyByIntrospector(UserInfo userInfo, String propertyName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(propertyName)) {
                    Method methodGetUserName = propDesc.getReadMethod();
                    Object objUserName = methodGetUserName.invoke(userInfo);
                    System.out.println("get userName:" + objUserName.toString());
                    break;
                }
            }
        }
    }

}
