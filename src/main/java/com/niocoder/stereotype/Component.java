package com.niocoder.stereotype;

import java.lang.annotation.*;

/**
 * Component 注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
