package com.fqlopes.customercontrol.deals.service;


import com.fqlopes.customercontrol.customers.entities.Customer;
import com.fqlopes.customercontrol.customers.repository.CustomerRepository;
import com.fqlopes.customercontrol.deals.dto.DealsDto;
import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import com.fqlopes.customercontrol.deals.entities.Deals;
import com.fqlopes.customercontrol.deals.repository.DealsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DealsService {

    private final DealsRepository repository;
    private final CustomerRepository customerRepository;
    private final DealsMapper mapper;


    public List<DealsResponseDto> findAll() {

        List<Deals> deals = repository.findAll();
        return deals.stream()
                .map(mapper::toDealsResponseDto)
                .toList();
    }

    public DealsResponseDto createDeal (DealsDto dto){
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException(" Customer not Found!"));

        Deals deal = mapper.toDeals(dto);
        deal.setCustomer(customer);
        Deals savedDeal = repository.save(deal);
        return mapper.toDealsResponseDto(savedDeal);
    }

}
