package com.taf.sample.framework.test_dev.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used for ols tests which are not being used right now.
 * <p>
 * Tests with this annotation will be ignored on test run.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DeprecatedTest {
    String[] description() default "";
}
