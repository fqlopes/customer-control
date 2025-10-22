package com.fqlopes.customercontrol.customers.dto;

import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponseDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
    private List<DealsResponseDto> deals;

}
