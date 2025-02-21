package com.backend.submission_service.exception;

import com.backend.submission_service.payload.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<Response> MyExceptionHandler(MyException e){
        return ResponseEntity.badRequest().body(new Response(e.getMessage()));
    }
}
