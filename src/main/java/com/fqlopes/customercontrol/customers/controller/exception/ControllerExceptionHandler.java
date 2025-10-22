package com.fqlopes.customercontrol.customers.controller.exception;

import com.fqlopes.customercontrol.customers.service.exception.CustomerNotFoundException;
import com.fqlopes.customercontrol.customers.service.exception.DuplicateResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    //customer not found
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<StandardError> customerNotFound(CustomerNotFoundException e, WebRequest request){

        StandardError error = new StandardError();
        error.setInstant(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //unique fields of customer already exists
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<StandardError> alreadyExists(DuplicateResourceException e, WebRequest request){

        StandardError error = new StandardError();
        error.setInstant(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Already exists");
        error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false).replace("uri=",""));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }




    //missing parameters
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> wrongDataExceptions(MethodArgumentNotValidException e,
                                                             WebRequest request){

        //Getting error messages
        List<ValidationError> messages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ValidationError(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        StandardError error = new StandardError();
        error.setInstant(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Missing parameters");
        error.setErrorMessages(messages);
        error.setMessage("Obligatory parameters!");
        error.setPath(request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.badRequest().body(error);
    }
}
