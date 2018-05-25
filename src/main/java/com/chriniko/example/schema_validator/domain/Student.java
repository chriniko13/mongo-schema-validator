package com.chriniko.example.schema_validator.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString

public class Student {

    @Id
    private String id;


    private String firstname;
    private String initials;
    private String surname;
}
