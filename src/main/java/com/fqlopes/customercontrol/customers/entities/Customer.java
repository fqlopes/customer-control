package com.fqlopes.customercontrol.customers.entities;


import com.fqlopes.customercontrol.deals.entities.Deals;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private String city;

    public Customer(String firstName, String lastName, String phoneNumber, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deals> dealings = new ArrayList<>();

}
