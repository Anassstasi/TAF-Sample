package com.taf.sample.framework.validators;

import com.taf.sample.configuration.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.asserts.SoftAssert;

@ContextConfiguration(classes = {AppConfig.class})
public class BaseValidator {

    @Autowired
    protected SoftAssert softAssert;

}
