/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.model.Order;
import com.filRouge.filRouge.service.OrderService;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import javassist.NotFoundException;
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

/**
 *
 * @author maxla
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    
    @Autowired
    public OrderService orderService;
    
   @GetMapping(value="/orders", produces = MediaType.APPLICATION_JSON_VALUE)
   @ApiOperation(value= "returns a list of order", nickname = "get All Orders", response = Order.class)
    public ResponseEntity getCustomers(){
        String serialized = "";
        List<Order> orders = this.orderService.findAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(orders));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }   
    }
    
    @RequestMapping(value="/order", method=RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Order order){
        orderService.save(order);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/order-{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateCustomer(@PathVariable("id") Long id, @RequestBody Order order){
        order.setId(id);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @RequestMapping(value="/order-{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getById(@PathVariable("id") Long id){
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value="/orderDelete-{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id){
       try {
           Order order = orderService.findById(id);
           orderService.delete(id);
       } catch (Exception e){
           return new ResponseEntity<>("Order doesnt exist", HttpStatus.NOT_FOUND);
       }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
