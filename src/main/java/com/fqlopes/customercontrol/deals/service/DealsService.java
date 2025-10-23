package com.fqlopes.customercontrol.deals.service;


import com.fqlopes.customercontrol.customers.entities.Customer;
import com.fqlopes.customercontrol.customers.repository.CustomerRepository;
import com.fqlopes.customercontrol.deals.dto.UpdateDealsDto;
import com.fqlopes.customercontrol.exceptions.customer.CustomerNotFoundException;
import com.fqlopes.customercontrol.deals.dto.DealsDto;
import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import com.fqlopes.customercontrol.deals.entities.Deals;
import com.fqlopes.customercontrol.deals.repository.DealsRepository;
import com.fqlopes.customercontrol.exceptions.deals.DealNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DealsService {

    private final DealsRepository repository;
    private final CustomerRepository customerRepository;
    private final DealsMapper mapper;

    //find All deals
    public List<DealsResponseDto> findAll() {

        List<Deals> deals = repository.findAll();
        return deals.stream()
                .map(mapper::toDealsResponseDto)
                .toList();
    }

    //creating
    public DealsResponseDto createDeal (DealsDto dto){
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(" Customer not found."));

        Deals deal = mapper.toDeals(dto);
        deal.setCustomer(customer);
        Deals savedDeal = repository.save(deal);
        return mapper.toDealsResponseDto(savedDeal);
    }

    //find by Id
    public DealsResponseDto findById (Integer id){
        Deals deal = repository.findById(id)
                .orElseThrow(() -> new DealNotFoundException("No such deal."));
        return mapper.toDealsResponseDto(deal);
    }

    //update
    public DealsResponseDto update(Integer id, UpdateDealsDto dto) {
        Deals current = repository.findById(id)
                .orElseThrow(() -> new DealNotFoundException ("No such deal."));

       mapper.updateDeals(current, dto);

        Deals saved = repository.save(current);
        return mapper.toDealsResponseDto(saved);
    }

    //delete
    public void delete (Integer id){
        Deals current = repository.findById(id)
                .orElseThrow(() -> new DealNotFoundException("No such deal"));
    }
}
