package com.niocoder.test.java.method;

public class ToGenericStringTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c1 = Class.forName("java.lang.String");
        Object instance = c1.newInstance();
        System.out.println("instance class : " + instance.getClass());
    }
}
