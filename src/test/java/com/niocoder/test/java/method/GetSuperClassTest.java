package com.niocoder.test.java.method;

public class GetSuperClassTest {
    public static void main(String[] args) throws Exception {
        System.out.println("getSuperclass-----------------------------------------");
        {
            Class myClass = GetSuperClassTest.class;
            Class c1 = Class.forName("com.niocoder.test.java.method.A");
            Class c2 = Class.forName("com.niocoder.test.java.method.B");
            Class c3 = Class.forName("java.lang.Object");
            System.out.print("GetSuperClassTest superclass : ");
            System.out.println(myClass.getSuperclass());
            System.out.print("A superclass : ");
            System.out.println(c1.getSuperclass());
            System.out.print("B superclass : ");
            System.out.println(c2.getSuperclass());
            System.out.print("Object superclass : ");
            System.out.println(c3.getSuperclass());
        }

        System.out.println("getGenericSuperclass-----------------------------------------");
        {
            Class myClass = GetSuperClassTest.class;
            Class c1 = Class.forName("java.util.ArrayList");
            Class c3 = Class.forName("java.lang.Object");
            System.out.print("GetSuperClassTest superclass : ");
            System.out.println(myClass.getGenericSuperclass());
            System.out.print("ArrayList superclass : ");
            System.out.println(c1.getGenericSuperclass());
            System.out.print("Object superclass : ");
            System.out.println(c3.getGenericSuperclass());
        }

        System.out.println("getInterfaces-----------------------------------------");
        {
            Class c1 = Class.forName("com.niocoder.test.java.method.D");
            Class c2 = Class.forName("java.lang.String");
            Class c1Interfaces[] = c1.getInterfaces();
            Class c2Interfaces[] = c2.getInterfaces();
            System.out.println("interfaces implemented by D class : ");
            for (Class class1 : c1Interfaces) {
                System.out.println(class1);
            }

            System.out.println("interfaces implemented by String class : ");
            for (Class class1 : c2Interfaces) {
                System.out.println(class1);
            }
        }

        System.out.println("getPackage-----------------------------------------");
        {
            Class c1 = Class.forName("java.lang.String");
            Class c2 = Class.forName("java.util.ArrayList");
            System.out.println(c1.getPackage());
            System.out.println(c2.getPackage());
        }
    }
}

class A {

}

class B extends A {

}

interface C {

}

interface D extends C {

}