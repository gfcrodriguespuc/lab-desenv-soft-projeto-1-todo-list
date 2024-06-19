package com.labdesenvsoft.todolist.domain.type;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import lombok.Getter;

@Getter
public class TaskStatus {

    private TaskStatusState state;
    private Integer days;
    private LocalDate dueDate;

    public TaskStatus(TodoType type, Instant createdAt, LocalDate dueDate, Integer deadline, boolean completed) {
        if (completed) {
            this.state = TaskStatusState.COMPLETED;
            return;
        }

        // XXX: Verificar como a timezone do servidor influencia no cálculo de dias
        switch (type) {
            case FREE:
                this.state = TaskStatusState.EXPECTED;
                break;
            case DUE_DATE:
                this.dueDate = dueDate;
                this.state = calculateState(this.dueDate);
                this.days = calculateDays(this.dueDate);
                break;
            case DEADLINE:
                // O deadline é a quantidade de dias que a tarefa tem para ser
                // concluída, assim, a data de vencimento é a data de criação
                // mais o prazo em dias
                LocalDate deadlineDueDate = createdAt
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .plusDays(deadline);
                this.dueDate = deadlineDueDate;
                this.state = calculateState(this.dueDate);
                this.days = calculateDays(this.dueDate);
                break;
            default:
                throw new IllegalArgumentException("Tipo de tarefa inesperado: " + type);
        }
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
