package com.labdesenvsoft.todolist.domain.entity;

import com.labdesenvsoft.todolist.domain.type.TaskStatus;
import com.labdesenvsoft.todolist.domain.type.TodoPriority;
import com.labdesenvsoft.todolist.domain.type.TodoType;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FreeTask extends Task {

    public FreeTask(
            TodoPriority priority,
            String description,
            Boolean completed) {
        super(TodoType.FREE, priority, description, completed);
    }

    @Override
    protected TaskStatus calculateStatus() {
        return new TaskStatus(isCompleted());
    }

}
