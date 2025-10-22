package com.fqlopes.customercontrol.deals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DealsResponseDto {

    private Integer id;
    private String projectName;
    private Integer customerId;

}
