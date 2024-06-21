package com.labdesenvsoft.todolist.domain.entity;

import java.time.Instant;

import com.labdesenvsoft.todolist.domain.type.TaskStatus;
import com.labdesenvsoft.todolist.domain.type.TodoPriority;
import com.labdesenvsoft.todolist.domain.type.TodoType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private TodoType type;

    @Column(nullable = false)
    private TodoPriority priority = TodoPriority.LOW; // 0 - LOW

    @Schema(description = "Descrição da tarefa, deve possuir no mínimo 10 caracteres")
    @Size(min = 10, message = "Descrição da tarefa deve possuir no mínimo 10 caracteres")
    private String description;

    private boolean completed;

    // https://prateek-ashtikar512.medium.com/how-to-calculate-non-persistent-property-via-hibernate-formula-6a20b51c4c86
    @Transient
    @Setter(AccessLevel.NONE)
    private TaskStatus status;

    public Task(TodoType type, TodoPriority priority, String description, Boolean completed) {
        this.type = type;
        this.description = description;

        if (priority != null) {
            this.priority = priority;
        }

        if (completed != null) {
            this.completed = completed;
        }
    }

    protected abstract TaskStatus calculateStatus();

    @PostLoad
    public void postLoad() {
        this.status = calculateStatus();
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" + completed + "]";
    }
}
