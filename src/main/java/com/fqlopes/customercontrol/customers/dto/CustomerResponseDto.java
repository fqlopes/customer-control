package com.fqlopes.customercontrol.customers.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponseDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
}
