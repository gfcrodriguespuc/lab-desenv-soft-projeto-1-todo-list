package com.labdesenvsoft.todolist.domain.entity;

import java.time.LocalDate;

import com.labdesenvsoft.todolist.domain.exception.TaskValidationException;
import com.labdesenvsoft.todolist.domain.type.TaskStatus;
import com.labdesenvsoft.todolist.domain.type.TodoPriority;
import com.labdesenvsoft.todolist.domain.type.TodoType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DueDateTask extends Task {

    @Column(columnDefinition = "DATE")
    private LocalDate dueDate;

    public DueDateTask(
            TodoPriority priority,
            String description,
            Boolean completed,
            LocalDate dueDate)
            throws TaskValidationException {
        super(TodoType.DUE_DATE, priority, description, completed);

        if (dueDate == null || dueDate.isBefore(LocalDate.now())) {
            throw new TaskValidationException(
                    "A data prevista de execução da tarefa deverá ser sempre igual ou superior a data atual.");
        }

        this.dueDate = dueDate;
    }

    @Override
    protected TaskStatus calculateStatus() {
        return new TaskStatus(dueDate, isCompleted());
    }
}
