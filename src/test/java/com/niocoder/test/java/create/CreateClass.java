package com.niocoder.test.java.create;

public class CreateClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("java.lang.String");
        Class c2 = String.class;
        String str = new String("string");
        Class c3 = str.getClass();
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
