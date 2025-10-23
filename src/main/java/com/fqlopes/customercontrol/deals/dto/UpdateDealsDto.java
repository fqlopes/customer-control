package com.fqlopes.customercontrol.deals.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDealsDto {

    @NotBlank
    private String projectName;

    @NotBlank
    private String address;

    private String details;
}
