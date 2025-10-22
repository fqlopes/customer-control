package com.fqlopes.customercontrol.deals.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealsDto {

    @NotBlank
    private String projectName;

    @NotBlank
    private String address;

    private String details;

    @NotNull
    private Integer customerId;

}
