package com.labdesenvsoft.todolist.controller.dto;

import java.time.LocalDate;

import com.labdesenvsoft.todolist.domain.entity.DeadlineTask;
import com.labdesenvsoft.todolist.domain.entity.DueDateTask;
import com.labdesenvsoft.todolist.domain.entity.FreeTask;
import com.labdesenvsoft.todolist.domain.entity.Task;
import com.labdesenvsoft.todolist.domain.exception.TaskValidationException;
import com.labdesenvsoft.todolist.domain.type.TodoPriority;
import com.labdesenvsoft.todolist.domain.type.TodoType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    public TodoType type;
    public TodoPriority priority;
    public LocalDate dueDate;
    public Integer deadline;
    public String description;
    public Boolean completed;

    public Task toTask() throws TaskValidationException {
        if (type == TodoType.DEADLINE) {
            return new DeadlineTask(priority, description, completed, deadline);
        } else if (type == TodoType.DUE_DATE) {
            return new DueDateTask(priority, description, completed, dueDate);
        } else {
            // type == TodoType.FREE
            // Para manter a compatibilidade com a versão anterior,
            // se o tipo não for informado, será considerado como tipo livre.
            return new FreeTask(priority, description, completed);
        }
    }
}
