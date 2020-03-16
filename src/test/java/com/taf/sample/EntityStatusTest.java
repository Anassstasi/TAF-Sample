package com.taf.sample;

import com.taf.sample.base.BaseTest;
import com.taf.sample.framework.constants.TestGroup;
import com.taf.sample.framework.test_dev.annotations.Group;
import com.taf.sample.framework.test_dev.annotations.Tag;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

@Epic(value = "Entity Status")
public class EntityStatusTest extends BaseTest {

    @Group(value = {TestGroup.SMOKE, TestGroup.REGRESSION})
    @Tag(value = {TestGroup.FEATURE_1, TestGroup.STATUS})
    @Test(description = "Test-case ID : [TAF-1] - test summary.")
    @Description("Extracted test description.")
    public void test() {

        //Test logic

    }
}
