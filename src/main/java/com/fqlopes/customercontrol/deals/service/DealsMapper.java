package com.fqlopes.customercontrol.deals.service;

import com.fqlopes.customercontrol.deals.dto.DealsDto;
import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import com.fqlopes.customercontrol.deals.dto.UpdateDealDto;
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

        String customerName = "";
        Integer customerId = null;

        if (deals.getCustomer() != null){
            customerId = deals.getCustomer().getId();
            customerName = deals.getCustomer().getFirstName() + " " + deals.getCustomer().getLastName();
        }

        return new DealsResponseDto(
                deals.getId(),
                deals.getProjectName(),
                customerId,
                customerName
                );
    }

    public void updateDeal (Deals current, UpdateDealDto dealsDto){

        current.setProjectName(dealsDto.getProjectName());
        current.setAddress(dealsDto.getAddress());
        current.setDetails(dealsDto.getDetails());
    }
}
