package com.fqlopes.customercontrol.deals.service;

import com.fqlopes.customercontrol.deals.dto.DealsDto;
import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import com.fqlopes.customercontrol.deals.entities.Deals;
import org.springframework.stereotype.Component;

@Component
public class DealsMapper {

    public Deals toDeals (DealsDto dealsDto){

        Deals newDeal = new Deals();
        newDeal.setProjectName(dealsDto.getProjectName());
        newDeal.setAddress(dealsDto.getAddress());
        newDeal.setDetails(dealsDto.getDetails());
        return newDeal;
    }

    public DealsResponseDto toDealsResponseDto (Deals deals){
        return new DealsResponseDto(deals.getId(), deals.getProjectName(), deals.getCustomer().getId());
    }
}
