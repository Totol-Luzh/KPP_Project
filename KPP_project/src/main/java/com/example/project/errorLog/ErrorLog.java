package com.example.project.errorLog;


import com.example.project.exceptions.BadRequestException;
import com.example.project.exceptions.ServerException;
import com.example.project.response.Response;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorLog extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handleException(@NonNull BadRequestException e) {
        logger.error("ERROR CODE 400", e);
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Response> handleException(@NonNull Exception e) {
        logger.error("ERROR CODE 500", e);
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
