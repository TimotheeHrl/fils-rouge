/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.service.CustomerService;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author maxla
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;   
    
    
    
    @GetMapping(value="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "returns a list of cutomers", nickname = "get All Customers", response = Customer.class)
    public ResponseEntity getCustomers(){
        String serialized = "";
        List<Customer> clients = this.customerService.findAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }   
    }
    
    @RequestMapping(value="/customer", method=RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value="/customer-{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        return new ResponseEntity(customer, HttpStatus.CREATED);
    }

    @RequestMapping(value="/customer-{id}",
            method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customer.setId(id);
        customerService.save(customer);
        return new ResponseEntity(customer, HttpStatus.OK);
    }
    
    @RequestMapping(value="/customerDelete-{id}", method=RequestMethod.DELETE)
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){

        try{
            Customer customer = customerService.findById(id);
            customerService.delete(id);
        }catch (Exception e){
            return new ResponseEntity("Customer not found", HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity(HttpStatus.OK);
    }


}
