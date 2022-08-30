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
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    

    private CustomerService customerService;

@Autowired
     public CustomerController(@Lazy CustomerService customerService){
        this.customerService = customerService;
}
    @GetMapping(value="/all")
    public ResponseEntity getCustomers(){
        String serialized = "";
        List<Customer> clients = this.customerService.findAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(clients));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(value="/add")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer, HttpServletRequest request) {
    try {
        System.out.println(customer.toString());
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(customerService.save(customer)));

    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
     }
    }

            @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
               @PutMapping(value="/{id}")
    public  ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        try {
            System.out.println(id.toString());
            Customer customerToUpdate = customerService.findById(id);


            if(customer.getLastname()!=null){
                customerToUpdate.setLastname(customer.getLastname());
            }
            if(customer.getFirstname()!=null){
                customerToUpdate.setFirstname(customer.getFirstname());
            }
            if(customer.getMail()!=null){
                customerToUpdate.setMail(customer.getMail());
            }
            if(customer.getPhone()!=null){
                customerToUpdate.setPhone(customer.getPhone());
            }
            if(customer.getAdress()!=null){
                customerToUpdate.setAdress(customer.getAdress());
            }
            if(customer.getCity()!=null){
                customerToUpdate.setCity(customer.getCity());
            }
            if(customer.getZipCode()!=null){
                customerToUpdate.setZipCode(customer.getZipCode());
            }
            if(customer.getCountry()!=null){
                customerToUpdate.setCountry(customer.getCountry());
            }
            if(customer.getCompany()!=null){
                customerToUpdate.setCompany(customer.getCompany());
            }
            if(customer.getActive() !=null){
                customerToUpdate.setActive(customer.getActive());
            }

            customerService.save(customerToUpdate);
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(customerToUpdate));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }



    @RolesAllowed({"ROLE_MODERATEUR"})
@GetMapping(value="/{mail}")
    public ResponseEntity<Object> getCustomerByMail(@PathVariable("mail") String mail) {
        try {

            Customer customer = customerService.findByMail(mail);
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(customer));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @RolesAllowed({"ROLE_MODERATEUR"})
    @DeleteMapping(value="/{mail}")
    public ResponseEntity<Object> deleteCustomerByMail(@PathVariable("mail") String mail) {
        try {
            customerService.deleteByMail(mail);
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString("Customer deleted"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }








}
