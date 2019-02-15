package com.niocoder.test.java.method;

public class IsInstanceTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c = Class.forName("java.lang.String");
        String url = "http://niocoder.com";
        int i = 10;
        boolean b1 = c.isInstance(url);
        boolean b2 = c.isInstance(i);
        System.out.println("is url instance of String : " + b1);
        System.out.println("is i instance of String : " + b2);
    }
}
