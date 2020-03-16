package com.taf.sample.framework.steps.validationSteps;

import com.taf.sample.entities.mongo.entity.Status;
import com.taf.sample.framework.steps.BaseSteps;
import com.taf.sample.framework.test_dev.context.Context;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class for validation steps.
 */
@Component
@Slf4j
public class ValidationSteps extends BaseSteps {

    /**
     * Validates entity's status is {entityStatus}.
     */
    @Step("Validate entity's status is {entityStatus}")
    public void validateEntityStatus(Status entityStatus) {
        String id = String.valueOf(testContext.getContext(Context.ID));

        mongoDbValidator.validateMigrationStatus(id, entityStatus);
    }
}
