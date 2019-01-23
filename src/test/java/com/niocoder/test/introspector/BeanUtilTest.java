package com.niocoder.test.introspector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilTest {

    public static void main(String[] args) {

        UserInfo userInfo = new UserInfo();
        try {

            BeanUtils.setProperty(userInfo, "userName", "郑龙飞");
            System.out.println("set userName:" + userInfo.getUserName());
            System.out.println("get userName:" + BeanUtils.getProperty(userInfo, "userName"));

            BeanUtils.setProperty(userInfo, "age", 18);
            System.out.println("set age:" + userInfo.getAge());
            System.out.println("get age:" + BeanUtils.getProperty(userInfo, "age"));
            System.out.println("get userName type:" + BeanUtils.getProperty(userInfo, "userName").getClass().getName());
            System.out.println("get age type:" + BeanUtils.getProperty(userInfo, "age").getClass().getName());

            PropertyUtils.setProperty(userInfo, "age", 8);
            System.out.println(PropertyUtils.getProperty(userInfo, "age"));
            System.out.println(PropertyUtils.getProperty(userInfo, "age").getClass().getName());
            // 特殊 age属性为Integer类型
            PropertyUtils.setProperty(userInfo, "age", "8");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
