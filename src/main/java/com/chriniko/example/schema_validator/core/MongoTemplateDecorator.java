package com.chriniko.example.schema_validator.core;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MongoTemplateDecorator {

    private static final String UNDEFINED = "undefined";

    private final MongoTemplate mongoTemplate;
    private final PopulatedTemplate populatedTemplate;

    @Autowired
    public MongoTemplateDecorator(MongoTemplate mongoTemplate, PopulatedTemplate populatedTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.populatedTemplate = populatedTemplate;
    }

    public void saveEntity(Map<String, Object> replacementMap, String templateName, String collectionName) {

        convertMapNullValuesToFallback(replacementMap);

        String populatedInfoToSave = populatedTemplate.populate(replacementMap, templateName);
        BasicDBObject basicDBObject = BasicDBObject.parse(populatedInfoToSave);
        mongoTemplate.insert(basicDBObject, collectionName);
    }

    private void convertMapNullValuesToFallback(Map<String, Object> replacementMap) {
        for (Map.Entry<String, Object> entry : replacementMap.entrySet()) {
            if (entry.getValue() == null) {
                replacementMap.put(entry.getKey(), UNDEFINED);
            }
        }
    }


}
