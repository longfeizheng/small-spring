package com.niocoder.test.java.method;

import java.lang.reflect.TypeVariable;

public class GetClassLoaderTest {
    public static void main(String[] args) throws Exception {
        {
            Class myClass = Class.forName("com.niocoder.test.java.method.GetClassLoaderTest");
            Class c1 = Class.forName("java.lang.String");
            Class c2 = int.class;
            System.out.print("GetClassLoaderTest class loader : ");
            System.out.println(myClass.getClassLoader());
            System.out.print("String class loader : ");
            System.out.println(c1.getClassLoader());

            System.out.print("primitive int loader : ");
            System.out.println(c2.getClassLoader());
        }

        {
            Class c = Class.forName("java.util.Set");
            TypeVariable[] tv = c.getTypeParameters();
            System.out.println("TypeVariables in "+c.getName()+" class : ");
            for (TypeVariable typeVariable : tv)
            {
                System.out.println(typeVariable);
            }
        }

    }
}
