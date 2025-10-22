package com.fqlopes.customercontrol.customers.controller;


import com.fqlopes.customercontrol.customers.dto.CustomerDto;
import com.fqlopes.customercontrol.customers.dto.CustomerResponseDto;
import com.fqlopes.customercontrol.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService service;

    //list all customers registered
    @GetMapping("/customers")
    public List<CustomerResponseDto> findAll(){
        return service.findAll();
    }

    //finding customer by id
    @GetMapping("/customers/{id}")
    public CustomerResponseDto findById (@PathVariable("id") Integer id){
        return service.findById(id);
    }

    //creating a customer with proper HTTP response
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> insertCustomer(@RequestBody @Valid CustomerDto customerDto){
        CustomerResponseDto saved = service.addCustomer(customerDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    //update
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Integer id,
                                                              @RequestBody @Valid CustomerDto customerDto){
        CustomerResponseDto updated = service.update(id, customerDto);
        return ResponseEntity.ok().body(updated);
    }

    //delete
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
