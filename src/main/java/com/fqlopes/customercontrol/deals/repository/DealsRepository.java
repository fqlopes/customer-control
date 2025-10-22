package com.fqlopes.customercontrol.deals.repository;

import com.fqlopes.customercontrol.deals.entities.Deals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealsRepository extends JpaRepository<Deals, Integer> {
}
