package com.fqlopes.customercontrol.exceptions.customer;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
