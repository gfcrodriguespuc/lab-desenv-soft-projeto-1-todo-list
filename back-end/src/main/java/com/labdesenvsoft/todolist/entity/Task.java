package com.labdesenvsoft.todolist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa uma tarefa da lista de tarefas")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Descrição da tarefa, deve possuir no mínimo 10 caracteres")
    @Size(min = 10, message = "Descrição da tarefa deve possuir no mínimo 10 caracteres")
    private String description;

    private boolean completed;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" + completed + "]";
    }
}
