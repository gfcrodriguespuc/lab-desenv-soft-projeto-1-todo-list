package com.labdesenvsoft.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @GetMapping("/tasks")
    public String getAllTasks() {
        return "All tasks";
    }
}
