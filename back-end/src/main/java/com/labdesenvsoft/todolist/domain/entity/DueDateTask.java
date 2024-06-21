package com.labdesenvsoft.todolist.domain.entity;

import java.time.LocalDate;

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
            LocalDate dueDate) {
        super(TodoType.DUE_DATE, priority, description, completed);
        this.dueDate = dueDate;
    }

    @Override
    protected TaskStatus calculateStatus() {
        return new TaskStatus(dueDate, isCompleted());
    }
}
