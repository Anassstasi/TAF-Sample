package com.taf.sample.entities.mongo.repository;

import com.taf.sample.entities.mongo.entity.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoEntityRepository extends MongoRepository<MongoEntity, String>, MongoEntityRepositoryCustom {

    MongoEntity findByNameIs();

    MongoEntity findById(String id);
}
