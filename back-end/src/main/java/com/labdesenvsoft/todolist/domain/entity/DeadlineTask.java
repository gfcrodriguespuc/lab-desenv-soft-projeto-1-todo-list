package com.labdesenvsoft.todolist.domain.entity;

import java.time.LocalDate;
import java.time.ZoneId;

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
public class DeadlineTask extends Task {

    private Integer deadline;

    public DeadlineTask(
            TodoPriority priority,
            String description,
            Boolean completed,
            Integer deadline) {
        super(TodoType.DEADLINE, priority, description, completed);
        this.deadline = deadline;
    }

    @Override
    protected TaskStatus calculateStatus() {
        // O deadline é a quantidade de dias que a tarefa tem para ser
        // concluída, assim, a data de vencimento é a data de criação
        // mais o prazo em dias
        LocalDate deadlineDueDate = this.getCreatedAt()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .plusDays(deadline);
        return new TaskStatus(deadlineDueDate, isCompleted());
    }
}
