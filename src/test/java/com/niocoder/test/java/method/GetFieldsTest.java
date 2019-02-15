package com.niocoder.test.java.method;

import java.lang.reflect.Field;

public class GetFieldsTest {
    public static void main(String[] args) throws Exception {
        Class c1 = Class.forName("java.lang.Integer");
        Field F[] = c1.getFields();

        System.out.println("Below are the fields of Integer class : ");
        for (Field field : F) {
            System.out.println(field);
        }
    }
}
