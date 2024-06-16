package com.labdesenvsoft.todolist.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatus {

    private TaskStatusState state;
    private int days;
    private Date dueDate;

}
