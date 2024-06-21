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

import com.labdesenvsoft.todolist.controller.dto.TaskDTO;
import com.labdesenvsoft.todolist.domain.entity.Task;
import com.labdesenvsoft.todolist.domain.exception.TaskNotFoundException;
import com.labdesenvsoft.todolist.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    @Operation(summary = "Busca todas as tarefas da lista de tarefas")
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        Collection<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/tasks")
    @Operation(summary = "Cria uma nova tarefa")
    public ResponseEntity<Void> postTask(@RequestBody TaskDTO taskToCreateDTO, UriComponentsBuilder uriBuilder) {
        Task taskToCreate = taskToCreateDTO.toTask();

        long taskId = taskService.createTask(taskToCreate);

        UriComponents uriComponents = uriBuilder
                .path("/tasks/{id}")
                .buildAndExpand(taskId);

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Busca uma tarefa específica")
    public ResponseEntity<Task> getTask(@PathVariable Long id) throws TaskNotFoundException {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Atualiza uma tarefa existente")
    public ResponseEntity<Void> putTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO)
            throws TaskNotFoundException {
        Task task = taskDTO.toTask();
        taskService.updateTask(id, task);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Deleta uma tarefa específica")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws TaskNotFoundException {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
