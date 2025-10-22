package com.fqlopes.customercontrol.customers.service;

import com.fqlopes.customercontrol.customers.dto.CustomerDto;
import com.fqlopes.customercontrol.customers.dto.CustomerResponseDto;
import com.fqlopes.customercontrol.customers.entities.Customer;
import com.fqlopes.customercontrol.customers.repository.CustomerRepository;
import com.fqlopes.customercontrol.customers.service.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    //creation
    public CustomerResponseDto addCustomer(CustomerDto dto) {
        Customer customer = mapper.toCustomer(dto);
        Customer saved = repository.save(customer);
        return mapper.toCustomerResponseDto(saved);
    }

    //listing all
    public List<CustomerResponseDto> findAll(){

        return repository.findAll()
                .stream()
                .map(mapper::toCustomerResponseDto)
                .collect(Collectors.toList());

    }

    //finding by id
    public CustomerResponseDto findById (Integer id){

        return repository.findById(id)
                .map(mapper::toCustomerResponseDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found!"));

    }

    //update
    public CustomerResponseDto update(Integer id, CustomerDto customerDto) {

            Customer current = repository.findById(id)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found!"));
            mapper.updateCustomer(current, customerDto);
            Customer saved = repository.save(current);
        return mapper.toCustomerResponseDto(saved);

    }

    public void delete(@Valid Integer id) {

        Customer current = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found!"));
        repository.delete(current);

    }
}
