package com.labdesenvsoft.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ApiDocumentationConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("To-do List API")
                        .version("v1.0.0")
                        .description("Laboratório de Desenvolvimento de Software\n\n" +
                                "Projeto 1\n\n" +
                                "Aplicação de lista de tarefas"));
    }

}
