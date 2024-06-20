package com.labdesenvsoft.todolist.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.labdesenvsoft.todolist.domain.entity.Task;
import com.labdesenvsoft.todolist.domain.exception.TaskNotFoundException;
import com.labdesenvsoft.todolist.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    @Operation(summary = "Busca todas as tarefas da lista de tarefas")
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        try {

            Collection<Task> tasks = taskService.getAllTasks();

            if (tasks.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            log.error("Erro ao buscar todas as tarefas", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/tasks")
    @Operation(summary = "Cria uma nova tarefa")
    public ResponseEntity<Void> postTask(@RequestBody Task taskToCreate, UriComponentsBuilder uriBuilder) {
        try {
            long taskId = taskService.createTask(taskToCreate);

            UriComponents uriComponents = uriBuilder
                    .path("/tasks/{id}")
                    .buildAndExpand(taskId);

            return ResponseEntity.created(uriComponents.toUri()).build();
        } catch (Exception e) {
            log.error("Erro ao criar uma nova tarefa", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Busca uma tarefa específica")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erro ao buscar uma tarefa específica", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Atualiza uma tarefa existente")
    public ResponseEntity<Void> putTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            taskService.updateTask(id, task);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erro ao atualizar uma tarefa", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Deleta uma tarefa específica")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erro ao deletar uma tarefa", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
