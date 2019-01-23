package com.niocoder.test.introspector;

public class BeanInfoTest {

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("merryyou");
        try {
            BeanInfoUtil.getProperty(userInfo, "userName");

            BeanInfoUtil.setProperty(userInfo, "userName");

            BeanInfoUtil.getProperty(userInfo, "userName");

            BeanInfoUtil.setPropertyByIntrospector(userInfo, "userName");

            BeanInfoUtil.getPropertyByIntrospector(userInfo, "userName");

            BeanInfoUtil.setProperty(userInfo, "age");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
