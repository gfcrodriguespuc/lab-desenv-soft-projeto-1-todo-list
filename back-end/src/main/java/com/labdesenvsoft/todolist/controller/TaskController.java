package com.labdesenvsoft.todolist.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labdesenvsoft.todolist.entity.Task;
import com.labdesenvsoft.todolist.repository.ITaskRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    ITaskRepository taskRepository;

    @GetMapping("/tasks")
    @Operation(summary = "Busca todas as tarefas da lista de tarefas")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {

            List<Task> tasks = new LinkedList<>();
            taskRepository.findAll().forEach(tasks::add);

            if (tasks.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            log.error("Erro ao buscar todas as tarefas", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
