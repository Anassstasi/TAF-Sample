package com.taf.sample.base;

import com.taf.sample.configuration.AppConfig;
import com.taf.sample.framework.steps.actionSteps.Steps;
import com.taf.sample.framework.steps.actionSteps.TestCaseSteps;
import com.taf.sample.framework.steps.actionSteps.TestDataCreationSteps;
import com.taf.sample.framework.steps.validationSteps.ValidationSteps;
import com.taf.sample.framework.test_dev.reporting.AllureRestAssured;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;

import javax.annotation.PostConstruct;

@ContextConfiguration(classes = {AppConfig.class})
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected Steps steps;
    @Autowired
    protected ValidationSteps validationSteps;
    @Autowired
    protected TestDataCreationSteps testDataCreationSteps;
    @Autowired
    protected TestCaseSteps testCaseSteps;

    @PostConstruct
    private void setAllureRestAssuredFilter() {
        RestAssured.replaceFiltersWith(new AllureRestAssured());
    }

    @AfterMethod
    public void tearDown() {
        testCaseSteps.cleanTestContext();
    }
}
