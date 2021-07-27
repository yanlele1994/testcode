package com.bjpowernode.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URI {
    // 只有属性名为value时，才可以在使用注解的时候@URL("xx")，否则就必须指定属性名@URL(属性名="xx")
    String value();
}
