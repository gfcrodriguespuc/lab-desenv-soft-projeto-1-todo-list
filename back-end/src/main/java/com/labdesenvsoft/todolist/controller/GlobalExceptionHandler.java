package com.labdesenvsoft.todolist.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.labdesenvsoft.todolist.domain.exception.TaskNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (statusCode.is5xxServerError()) {
            log.error("Erro interno do servidor", ex);
        } else if (statusCode.is4xxClientError()) {
            log.trace("Erro de cliente ({})", statusCode, ex);
        } else {
            log.warn("Erro não esperado ({})", statusCode, ex);
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail body = createProblemDetail(
                ex,
                status,
                "Falha ao ler a requisição: " + ex.getLocalizedMessage(),
                null,
                null,
                request);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * Lida com todas as exceções lançadas pelo sistema durante o processamento
     * de uma requisição, exceto se for uma exceção do próprio Spring, pois
     * nesse caso ela será tratada no método handleException proprio do
     * ResponseEntityExceptionHandler.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof TaskNotFoundException theEx) {
            return handleNotFoundException(theEx, headers, request);
        }

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String defaultDetail = "Ocorreu um erro interno: " + ex.getLocalizedMessage();
        ProblemDetail body = createProblemDetail(
                ex,
                status,
                defaultDetail,
                null,
                null,
                request);

        return handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleNotFoundException(
            TaskNotFoundException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemDetail body = createProblemDetail(
                ex,
                status,
                ex.getLocalizedMessage(),
                null,
                null,
                request);

        return handleExceptionInternal(ex, body, headers, status, request);
    }
}
