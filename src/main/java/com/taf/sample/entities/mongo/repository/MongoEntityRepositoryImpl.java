package com.taf.sample.entities.mongo.repository;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoEntityRepositoryImpl implements MongoEntityRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertFromJSON(String jsonString) {
        DBObject dbObject = (DBObject) JSON.parse(jsonString);

        mongoTemplate.insert(dbObject, "collection_name");
    }
}
