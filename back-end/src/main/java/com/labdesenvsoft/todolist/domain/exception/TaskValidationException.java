package com.labdesenvsoft.todolist.domain.exception;

public class TaskValidationException extends Exception {
    public TaskValidationException(String message) {
        super(message);
    }

    public TaskValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
