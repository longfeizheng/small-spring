package com.niocoder.test.java.method;

public class ToStringTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("java.lang.String");
        Class c2 = int.class;
        Class c3 = void.class;
        System.out.println("Class represented by c1: "+c1.toString());
        System.out.println("Class represented by c2: "+c2.toString());
        System.out.println("Class represented by c3: "+c3.toString());
    }
}
