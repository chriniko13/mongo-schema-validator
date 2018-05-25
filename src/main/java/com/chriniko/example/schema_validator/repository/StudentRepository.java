package com.chriniko.example.schema_validator.repository;

import com.chriniko.example.schema_validator.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
