package com.salgadosdama.agenda.Controller.advice;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler
  public ResponseEntity<String> handleNotFound(ChangeSetPersister.NotFoundException exception){
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }
}
