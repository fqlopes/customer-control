package com.fqlopes.customercontrol.deals.repository;

import com.fqlopes.customercontrol.deals.entities.Deals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealsRepository extends JpaRepository<Deals, Integer> {

    //track the customer id;
    List<Deals> findByCustomerId(Integer customerId);

    //findAll

}
