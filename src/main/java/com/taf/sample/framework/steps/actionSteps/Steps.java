package com.taf.sample.framework.steps.actionSteps;

import com.taf.sample.framework.steps.BaseSteps;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class for test steps.
 */
@Component
@Slf4j
public class Steps extends BaseSteps {

    /**
     * Sends GET request to domain service.
     */
    @Step("Sends GET request to domain service.")
    public void sendGetRequestToDomainService() {
        restService.startDomainJob();
    }
}
