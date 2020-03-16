package com.taf.sample.entities.mongo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taf.sample.entities.base.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "collection_name")
public class MongoEntity extends BaseEntity {
    @Id
    private String id;

    @Field("name")
    @JsonProperty("name")
    private String name;

    @Field("number")
    @JsonProperty("number")
    private String number;

    @Field("status")
    @JsonProperty("status")
    private String status;
}
