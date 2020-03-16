package com.taf.sample.framework.steps.actionSteps;

import com.fasterxml.jackson.databind.JsonNode;
import com.taf.sample.entities.mongo.entity.MongoEntity;
import com.taf.sample.framework.steps.BaseSteps;
import com.taf.sample.framework.test_dev.context.Context;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for the steps which create and insert test data.
 */
@Component
@Slf4j
public class TestDataCreationSteps extends BaseSteps {

    /**
     * Adds an object to collection.
     */
    @Step("Add an object to collection")
    public void addObjectToDB(MongoEntity mongoEntity) throws IOException {
        testContext.setTestContext(Context.ID, mongoEntity.getId());

        mongoEntityRepository.save(mongoEntity);

        Allure.addAttachment("Inserted Object", "text/json", mongoEntity.toJson());
    }

    /**
     * Inserts an object to the collection in MongoDB.
     *
     * @param json object json string.
     */
    @Step("Add an object to DB from JSON string.")
    public void addToDBFromJson(String json) throws IOException {
        JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
        testContext.setTestContext(Context.ENTITY_NUMBER, jsonNode.get("number").asText());

        mongoEntityRepository.insertFromJSON(json);
        Allure.addAttachment("Inserted Object", "text/json", json);
    }

    /**
     * Inserts an object to the collection in MongoDB.
     */
    @Step("Add an object to the collection in MongoDB.")
    public void readExistingElements() {
        List<MongoEntity> objectsList = mongoEntityRepository.findAll();

        List<String> numbers = objectsList.stream().map(MongoEntity::getNumber)
                .collect(Collectors.toList());
        testContext.setTestListContext(Context.ENTITY_NUMBERS, numbers.stream()
                .map(Object::toString).collect(Collectors.toList()));
    }
}
