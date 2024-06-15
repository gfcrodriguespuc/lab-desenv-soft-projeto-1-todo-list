package com.labdesenvsoft.todolist.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HealthzController {
    public record Healthz(long count, String message) {
    }

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/healthz")
    @Operation(summary = "Busca o Health Check da aplicação")
    public ResponseEntity<Healthz> healthz() {
        return ResponseEntity.ok(new Healthz(counter.incrementAndGet(), "OK"));
    }
}
