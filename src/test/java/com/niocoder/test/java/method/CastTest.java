package com.niocoder.test.java.method;

public class CastTest {
    public static void main(String[] args) {
        {
            A1 a = new A1();
            System.out.println(a.getClass());
            B1 b = new B1();
            a = A1.class.cast(b);
            System.out.println(a.getClass());
        }

        {
            A1 a = new A1();
            Class superClass = a.getClass();
            B1 b = new B1();
            Class subClass = b.getClass();
            Class cast = subClass.asSubclass(superClass);
            System.out.println(cast);
        }

    }
}

class A1 {

}

class B1 extends A1 {

}
