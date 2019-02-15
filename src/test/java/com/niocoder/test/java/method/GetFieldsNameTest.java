package com.niocoder.test.java.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetFieldsNameTest {
    public static void main(String[] args) throws Exception {
        System.out.println("getField--------------------------------------------");
        {
            Class c1 = Class.forName("java.lang.Integer");

            Field f = c1.getField("MIN_VALUE");

            System.out.println("public field in Integer class with MIN_VALUE name :");
            System.out.println(f);
        }
        System.out.println("getMethod--------------------------------------------");
        {
            Class c1 = Class.forName("java.lang.Integer");
            Class c2 = Class.forName("java.lang.String");
            Method m = c1.getMethod("parseInt", c2);

            System.out.println("method in Integer class specified by parseInt : ");
            System.out.println(m);
        }
        System.out.println("getConstructor--------------------------------------------");
        {
            Class c1 = Class.forName("java.lang.Integer");
            Class c2 = Class.forName("java.lang.String");
            Constructor c = c1.getConstructor(c2);

            System.out.println("Constructor in Integer class & String parameterType:");
            System.out.println(c);
        }
    }
}
