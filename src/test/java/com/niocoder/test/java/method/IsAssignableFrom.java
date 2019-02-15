package com.niocoder.test.java.method;

public class IsAssignableFrom extends Thread {
    public static void main(String[] args) throws Exception {
        Class myClass = Class.forName("com.niocoder.test.java.method.IsAssignableFrom");
        Class c1 = Class.forName("java.lang.Thread");
        Class c2 = Class.forName("java.lang.String");

        boolean b1 = c1.isAssignableFrom(myClass);
        boolean b2 = c2.isAssignableFrom(myClass);

        System.out.println("is Thread class Assignable from Test : " + b1);
        System.out.println("is String class Assignable from Test : " + b2);
    }
}
