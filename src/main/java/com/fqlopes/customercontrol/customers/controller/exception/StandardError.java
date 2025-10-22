package com.fqlopes.customercontrol.customers.controller.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StandardError {

    private LocalDateTime instant;
    private Integer status;
    private String error;
    private List<ValidationError> errorMessages;
    private String message;
    private String path;


}
