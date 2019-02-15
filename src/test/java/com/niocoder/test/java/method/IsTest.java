package com.niocoder.test.java.method;

public class IsTest {

    @interface B
    {
        // Annotation element definitions
    }

    //枚举
    enum Color {
        RED, GREEN, BLUE;
    }

    //  Member class
    class A {
    }

    public static void main(String[] args) {
        // 匿名类
        IsTest t1 = new IsTest() {

        };

        Class c1 = t1.getClass();
        Class c2 = IsTest.class;
        Class c3 = A.class;
        Class c4 = Color.class;
        Class c5 = B.class;

        boolean b1 = c1.isAnonymousClass();
        System.out.println("is " + c1.toString() + " an anonymous class : " + b1);
        boolean b2 = c2.isLocalClass();
        System.out.println("is " + c2.toString() + " a local class : " + b2);
        boolean b3 = c3.isMemberClass();
        System.out.println("is " + c3.toString() + " a member class : " + b3);
        boolean b4 = c4.isEnum();
        System.out.println("is " + c3.toString() + " a Enum class : " + b4);
        boolean b5 = c5.isAnnotation();
        System.out.println("is " + c3.toString() + " an annotation  : " + b5);
    }
}
