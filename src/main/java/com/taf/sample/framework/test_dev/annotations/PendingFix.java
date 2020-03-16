package com.taf.sample.framework.test_dev.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * To mark not-working tests which functionality is not working yet and pending fix or implementation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PendingFix {
    String[] description() default "";
}
