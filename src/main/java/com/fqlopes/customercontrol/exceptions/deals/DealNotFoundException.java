package com.fqlopes.customercontrol.exceptions.deals;

public class DealNotFoundException extends RuntimeException {
    public DealNotFoundException(String message) {
        super(message);
    }
}
