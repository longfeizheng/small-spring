package com.niocoder.test.java.method;

import java.lang.reflect.Method;

public class GetMethodsTest {
    public static void main(String[] args) throws Exception {
        Class c1 = Class.forName("java.lang.Object");
        Method M[] = c1.getMethods();

        System.out.println("Below are the methods of Object class : ");
        for (Method method : M) {
            System.out.println(method);
        }
    }
}
