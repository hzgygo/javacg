package com.hzgy.core.annotation;

import java.lang.annotation.*;

/**
 * 线程安全标志
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
}
