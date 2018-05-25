package com.chriniko.example.schema_validator.service;

import com.chriniko.example.schema_validator.core.MongoTemplateDecorator;
import com.chriniko.example.schema_validator.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final MongoTemplateDecorator mongoTemplate;

    @Autowired
    public StudentService(MongoTemplateDecorator mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void store(Student student) {

        Map<String, Object> replacements = new HashMap<>();
        replacements.put("id", student.getId());
        replacements.put("firstname", student.getFirstname());
        replacements.put("initials", student.getInitials());
        replacements.put("surname", student.getSurname());
        replacements.put("_class", student.getClass().getName());

        mongoTemplate.saveEntity(replacements, "student.json", "students");
    }

    public Student find(String id) {
        return mongoTemplate.getActual().findOne(Query.query(Criteria.where("id").is(id)), Student.class);
    }

}
