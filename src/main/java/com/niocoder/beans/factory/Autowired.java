package com.niocoder.beans.factory;

import java.lang.annotation.*;

/**
 * Autowired注解
 */
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    boolean required() default false;
}
