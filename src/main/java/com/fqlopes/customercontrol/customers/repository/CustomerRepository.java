package com.fqlopes.customercontrol.customers.repository;


import com.fqlopes.customercontrol.customers.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //methods for custom exception of unique fields
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

}
