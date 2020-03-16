package com.taf.sample.framework.test_dev.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retries failed tests.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int counter = 0;
    private int retryLimit = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess() && counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}