package com.chriniko.example.schema_validator.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.chriniko.example.schema_validator")
@EnableAutoConfiguration
public class AppConfiguration {
}
