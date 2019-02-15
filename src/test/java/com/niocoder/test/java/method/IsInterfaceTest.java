package com.niocoder.test.java.method;

public class IsInterfaceTest {
    public static void main(String[] args) throws Exception {
        Class c1 = Class.forName("java.lang.String");
        Class c2 = Class.forName("java.lang.Runnable");
        boolean b1 = c1.isInterface();
        boolean b2 = c2.isInterface();
        System.out.println("is java.lang.String an interface : " + b1);
        System.out.println("is java.lang.Runnable an interface : " + b2);
    }
}
