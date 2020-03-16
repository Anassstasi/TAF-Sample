package com.taf.sample.framework.test_dev.listeners;

import com.taf.sample.framework.test_dev.annotations.*;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Sets implicit retry annotation to all the tests.
 * Excludes tests with @Bug annotation.
 */
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        String[] groups = {};
        String[] tags = {};
        if (!method.isAnnotationPresent(Bug.class)) {
            iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
        if (!method.isAnnotationPresent(PendingFix.class)) {
            iTestAnnotation.setEnabled(true);
        }
        if (method.isAnnotationPresent(DeprecatedTest.class)) {
            iTestAnnotation.setEnabled(false);
        }
        if (method.isAnnotationPresent(Group.class)) {
            groups = method.getAnnotation(Group.class).value();
        }
        if (method.isAnnotationPresent(Tag.class)) {
            tags = method.getAnnotation(Tag.class).value();
        }
        iTestAnnotation.setGroups(ArrayUtils.addAll(groups, tags));
    }
}
