package com.fqlopes.customercontrol.customers.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotBlank (message = "First Name is required!")
    private String firstName;

    @NotBlank (message = "Last Name is required!")
    private String lastName;

    @NotBlank(message = "Phone number is required!")
    @Pattern(regexp = "\\d{10,11}", message = "Phone must be composed by digits, between 10 to 11 digits only!")
    private String phoneNumber;

    @NotBlank (message = "Email is required!")
    private String email;

    @NotBlank(message = "Your city location is required!")
    private String city;

}
