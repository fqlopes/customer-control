package com.fqlopes.customercontrol.deals.controller;


import com.fqlopes.customercontrol.deals.dto.DealsDto;
import com.fqlopes.customercontrol.deals.dto.DealsResponseDto;
import com.fqlopes.customercontrol.deals.service.DealsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class DealsController {

    private final DealsService service;

    //list all Deals

    @GetMapping("/deals")
    public List<DealsResponseDto> findAll(){
        return service.findAll();
    }

    @PostMapping("/deals")
    public ResponseEntity<DealsResponseDto> addDeal(@Valid @RequestBody DealsDto dto){
        DealsResponseDto saved = service.createDeal(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);
    }
}
