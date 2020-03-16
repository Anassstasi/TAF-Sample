package com.taf.sample.framework.validators;

import com.taf.sample.entities.mongo.entity.MongoEntity;
import com.taf.sample.entities.mongo.entity.Status;
import com.taf.sample.entities.mongo.repository.MongoEntityRepository;
import com.taf.sample.framework.errorMsgs.AssertionMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

/**
 * Validation class for domain in MongoDB.
 */
@Component
public class MongoDbValidator extends BaseValidator {
    @Autowired
    private MongoEntityRepository mongoEntityRepository;

    /**
     * Validates status of object in mongoDB.
     *
     * @param id             object id.
     * @param expectedStatus expected status.
     */
    public void validateMigrationStatus(String id, Status expectedStatus) {
        MongoEntity mongoEntity = mongoEntityRepository.findById(id);

        Assert.assertEquals(mongoEntity.getStatus(), expectedStatus,
                String.format(AssertionMessages.INCORRECT_STATUS, id,
                        mongoEntity.getStatus(), expectedStatus));
    }
}
