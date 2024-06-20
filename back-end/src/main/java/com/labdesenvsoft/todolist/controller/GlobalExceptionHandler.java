package com.labdesenvsoft.todolist.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // XXX: Aqui pode ser feito o log das exceções
    // @Override
    // protected ResponseEntity<Object> handleExceptionInternal(
    // Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode,
    // WebRequest request) {
    // // TODO Auto-generated method stub
    // return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    // }

    /**
     * Lida com todas as exceções lançadas pelo sistema durante o processamento
     * de uma requisição, exceto se for uma exceção do próprio Spring, pois
     * nesse caso ela será tratada no método handleException proprio do
     * ResponseEntityExceptionHandler.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) throws Exception {
        // return super.handleException(ex, request);

        HttpHeaders headers = new HttpHeaders();

        // Object[] args = {ex.getPropertyName(), ex.getValue()};
        // Object[] args = { "getPropertyName", "getValue" };
        // String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" +
        // args[1] + "'";
        // String messageCode =
        // ErrorResponse.getDefaultDetailMessageCode(TypeMismatchException.class,
        // null);
        // String messageCode = ErrorResponse.getDefaultDetailMessageCode(ex.getClass(),
        // null);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        // ProblemDetail body = createProblemDetail(ex, status, defaultDetail,
        // messageCode, args, request);

        String defaultDetail = "Ocorreu um erro interno: " + ex.getLocalizedMessage();
        ProblemDetail body = createProblemDetail(
                ex,
                status,
                defaultDetail,
                null,
                null,
                request);

        return handleExceptionInternal(ex, body, headers, status, request);

        // // return createResponseEntity(body, headers, statusCode, request);
    }

    // @ExceptionHandler(Exception.class)
    // // public ResponseEntity<?> handleGenericException(Exception ex, WebRequest
    // // request) {
    // public ProblemDetail handleGenericException(Exception e, WebRequest request)
    // {
    // // return ResponseEntity(
    // // ApiError("An internal server error occurred: ${ex.localizedMessage}"),
    // // HttpHeaders(),
    // // HttpStatus.INTERNAL_SERVER_ERROR,
    // // )
    // // return ResponseEntity
    // // .internalServerError()
    // // .body("Ocorreu um erro interno do servidor: " + ex.getMessage());

    // // return
    // ResponseStatusException(org.springframework.http.HttpStatus.FORBIDDEN,
    // // "Ocorreu um erro interno do servidor: " + ex.getMessage());

    // ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
    // HttpStatus.NOT_FOUND,
    // "Ocorreu um erro interno do servidor: " + e.getLocalizedMessage());

    // // problemDetail.setTitle("Numero nao informado para Sorteio");
    // // problemDetail.setDetail("É preciso informar um numero de base para o
    // sorteio.
    // // Ex : /sorteiaNumero/80");
    // // problemDetail.setProperty("StackTrace", e.getStackTrace());
    // // problemDetail.setProperty("Categoria", "Plataforma");
    // // problemDetail.setProperty("TimeStamp", Instant.now());
    // return problemDetail;
    // }

}
