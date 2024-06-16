package com.labdesenvsoft.todolist.entity;

import java.time.OffsetDateTime;
import java.util.Date;

import com.labdesenvsoft.todolist.domain.TaskStatus;
import com.labdesenvsoft.todolist.domain.TodoPriority;
import com.labdesenvsoft.todolist.domain.TodoType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    // https://stackoverflow.com/questions/68154679/how-to-include-check-in-jpa-column-columndefinition
    @Column(columnDefinition = "SMALLINT DEFAULT 0 CHECK (type between 0 and 2)", nullable = false)
    private TodoType type = TodoType.FREE; // 0 - FREE

    @Column(columnDefinition = "SMALLINT DEFAULT 0 CHECK (priority between 0 and 2)", nullable = false)
    private TodoPriority priority = TodoPriority.LOW; // 0 - LOW

    private Date dueDate;

    private Integer deadline;

    @Schema(description = "Descrição da tarefa, deve possuir no mínimo 10 caracteres")
    @Size(min = 10, message = "Descrição da tarefa deve possuir no mínimo 10 caracteres")
    private String description;

    private boolean completed;

    // https://prateek-ashtikar512.medium.com/how-to-calculate-non-persistent-property-via-hibernate-formula-6a20b51c4c86
    @Transient
    private TaskStatus status;

    public Task(String description) {
        this.description = description;
    }

    @PostLoad
    public void postLoad() {
        // TODO: Implementar lógica para calcular o status da tarefa
        System.out.println("PostLoad");
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" + completed + "]";
    }
}
