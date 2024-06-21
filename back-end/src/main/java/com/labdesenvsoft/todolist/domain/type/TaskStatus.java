package com.labdesenvsoft.todolist.domain.type;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class TaskStatus {

    private TaskStatusState state;
    private Integer days;
    private LocalDate dueDate;

    public TaskStatus(LocalDate dueDate, boolean completed) {
        if (completed) {
            this.state = TaskStatusState.COMPLETED;
            return;
        }

        if (dueDate == null) {
            this.state = TaskStatusState.EXPECTED;
            return;
        }

        // XXX: Verificar como a timezone do servidor influencia no cálculo de dias
        this.state = calculateState(dueDate);
        this.days = calculateDays(dueDate);
        this.dueDate = dueDate;
    }

    public TaskStatus(boolean completed) {
        this(null, completed);
    }

    private static int calculateDays(LocalDate dueDate) {
        return dueDate.compareTo(LocalDate.now());
    }

    private static TaskStatusState calculateState(LocalDate dueDate) {
        return LocalDate.now().isAfter(dueDate)
                ? TaskStatusState.LATE
                : TaskStatusState.EXPECTED;
    }
}
