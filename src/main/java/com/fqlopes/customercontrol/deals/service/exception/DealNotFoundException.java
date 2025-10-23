package com.fqlopes.customercontrol.deals.service.exception;

public class DealNotFoundException extends RuntimeException {
    public DealNotFoundException(String message) {
        super(message);
    }
}
