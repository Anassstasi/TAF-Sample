package com.taf.sample.entities.mongo.repository;

public interface MongoEntityRepositoryCustom {
    /**
     * Inserts documents into DB from JSON file or string.
     *
     * @param json JSON string.
     */
    void insertFromJSON(String json);
}
