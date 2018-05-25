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
        String document = BasicDBObject.parse(populatedInfoToSave).toJson();

        mongoTemplate.insert(document, collectionName);
    }

    public MongoTemplate getActual() {
        return this.mongoTemplate;
    }

    private void convertMapNullValuesToFallback(Map<String, Object> replacementMap) {
        for (Map.Entry<String, Object> entry : replacementMap.entrySet()) {
            if (entry.getValue() == null) {
                replacementMap.put(entry.getKey(), UNDEFINED);
            }
        }
    }


}
