package com.niocoder.test.java.method;

public class IsArrayTest {
    public static void main(String[] args) {
        int a[] = new int[2];
        Class c1 = a.getClass();
        Class c2 = IsArrayTest.class;

        boolean b1 = c1.isArray();
        boolean b2 = c2.isArray();

        System.out.println("is "+c1.toString()+" an array : " + b1);
        System.out.println("is "+c2.toString()+" an array : " + b2);
    }
}
