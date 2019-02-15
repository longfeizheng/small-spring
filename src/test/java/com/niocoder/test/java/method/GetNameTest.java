package com.niocoder.test.java.method;

public class GetNameTest {
    public static void main(String[] args)
    {
        Class c = GetNameTest.class;

        System.out.print("Class Name associated with c : ");
        System.out.println(c.getName());
        System.out.println(c.getSimpleName());
    }
}
