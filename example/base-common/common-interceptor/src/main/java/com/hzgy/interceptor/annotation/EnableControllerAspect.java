package com.hzgy.interceptor.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AopRegister.class)
public @interface EnableControllerAspect {

    @AliasFor("scanPackages")
    String[] value() default {};

    @AliasFor("value")
    String[] scanPackages() default {};
}
