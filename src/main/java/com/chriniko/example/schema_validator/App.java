package com.chriniko.example.schema_validator;

import com.chriniko.example.schema_validator.domain.Student;
import com.chriniko.example.schema_validator.repository.StudentRepository;
import com.chriniko.example.schema_validator.service.StudentService;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {

        // --- 1st example ---
        studentRepository.deleteAll();

        List<Student> students = List.rangeClosed(1, 10)
                .map(idx -> new Student(UUID.randomUUID().toString(),
                        "firstname" + idx,
                        "initials" + idx,
                        "surname" + idx));

        studentRepository.saveAll(students);


        // --- 2nd example ---
        studentService.store(new Student(UUID.randomUUID().toString(),
                "bruno",
                null, "the best dog"));

    }
}
