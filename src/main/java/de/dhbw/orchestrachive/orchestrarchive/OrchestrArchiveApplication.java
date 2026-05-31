package de.dhbw.orchestrachive.orchestrarchive;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "OrchestrArchive API",
        version = "0.1.0",
        description = "Sheet music management system for orchestras"
))

@SpringBootApplication
public class OrchestrArchiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestrArchiveApplication.class, args);
    }

}
