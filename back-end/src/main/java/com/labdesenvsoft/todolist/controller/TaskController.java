package com.labdesenvsoft.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TaskController {

    @GetMapping("/tasks")
    @Operation(summary = "Busca todas as tarefas da lista de tarefas")
    public String getAllTasks() {
        return "All tasks";
    }
}
