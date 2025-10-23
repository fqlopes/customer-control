package com.fqlopes.customercontrol.customers.service;

import com.fqlopes.customercontrol.customers.dto.CustomerDto;
import com.fqlopes.customercontrol.customers.dto.CustomerResponseDto;
import com.fqlopes.customercontrol.customers.entities.Customer;
import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import com.fqlopes.customercontrol.deals.service.DealsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class CustomerMapper {

    private final DealsMapper dealsMapper;

    public Customer toCustomer (CustomerDto customerDto){

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerDto.getFirstName());
        newCustomer.setLastName(customerDto.getLastName());
        newCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        newCustomer.setEmail(customerDto.getEmail());
        newCustomer.setCity(customerDto.getCity());

        return newCustomer;
    }

    public CustomerResponseDto toCustomerResponseDto (Customer customer){

        List<DealsResponseDto> deals = customer.getDealings().stream()
                .map(dealsMapper::toDealsResponseDto)
                .toList();

        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCity(),
                deals
        );
    }

    public void updateCustomer (Customer current, CustomerDto customerDto){

        current.setFirstName(customerDto.getFirstName());
        current.setLastName(customerDto.getLastName());
        current.setPhoneNumber(customerDto.getPhoneNumber());
        current.setEmail(customerDto.getEmail());
        current.setCity(customerDto.getCity());

    }

}
