package com.fqlopes.customercontrol.exceptions.customer;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
