package com.niocoder.test.java.method;

import java.lang.reflect.Constructor;

public class GetConstructorsTest {
    public static void main(String[] args) throws Exception {
        Class c1 = Class.forName("java.lang.Boolean");
        Constructor C[] = c1.getConstructors();

        System.out.println("Below are the constructors of Boolean class :");
        for (Constructor constructor : C) {
            System.out.println(constructor);
        }
    }
}
