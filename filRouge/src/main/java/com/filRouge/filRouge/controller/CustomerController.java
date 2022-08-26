/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.controller;

import com.filRouge.filRouge.service.CustomerService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filRouge.filRouge.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author maxla
 */
@RestController
@Api(tags = "/")
@RequiredArgsConstructor
public class CustomerController {
    

    private CustomerService customerService;

@Autowired
     public CustomerController(@Lazy CustomerService customerService){
        this.customerService = customerService;

}


    @GetMapping(value="/customers")
    public ResponseEntity getCustomers(){
        String serialized = "";
        List<Customer> clients = this.customerService.findAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        
    }
    @RolesAllowed({"ROLE_MODERATEUR"})
    @RequestMapping(value="/customer", method= RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer, HttpServletRequest request) {
    try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(customerService.save(customer)));

    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
     }
    }


}
