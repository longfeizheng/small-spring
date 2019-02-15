package com.niocoder.test.java.method;

public class IsPrimitiveTest {
    public static void main(String[] args) {
        Class c1 = int.class;
        Class c2 = IsPrimitiveTest.class;
        boolean b1 = c1.isPrimitive();
        boolean b2 = c2.isPrimitive();

        System.out.println("is " + c1.toString() + " primitive : " + b1);
        System.out.println("is " + c2.toString() + " primitive : " + b2);
    }
}
