package com.taf.sample.framework.steps.actionSteps;

import com.taf.sample.framework.steps.BaseSteps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class for test case steps.
 */
@Component
@Slf4j
public class TestCaseSteps extends BaseSteps {

    /**
     * Cleans test context.
     */
    public void cleanTestContext() {
        testContext.clean();
    }
}
