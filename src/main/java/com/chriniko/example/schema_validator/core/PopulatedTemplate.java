package com.chriniko.example.schema_validator.core;

import org.apache.commons.text.StrSubstitutor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Component
public class PopulatedTemplate {

    String populate(Map<String, Object> replacementMap, String templateName) {

        try {
            URL url = this.getClass().getClassLoader().getResource("templates/" + templateName);
            assert url != null;
            URI uri = url.toURI();
            String loadedTemplate = Files.readAllLines(Paths.get(uri)).stream().reduce("", String::concat);

            return StrSubstitutor.replace(loadedTemplate, replacementMap);

        } catch (Throwable o_O) {
            throw new IllegalStateException("PopulatedTemplate#populate --- error occurred!");
        }
    }

}
