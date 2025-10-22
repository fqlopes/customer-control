package com.fqlopes.customercontrol.deals.entities;


import com.fqlopes.customercontrol.customers.entities.Customer;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Deals {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String projectName;
    private String address;
    private String details;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
