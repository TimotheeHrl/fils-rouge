/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.model.Order;
import com.filRouge.filRouge.service.CustomerService;
import com.filRouge.filRouge.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author maxla
 */
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;


   @Autowired
    public OrderController(@Lazy OrderService orderService, @Lazy CustomerService customerService){
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(orderService.findById(id)));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrders() {
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString( orderService.findAllWithCustomer()));
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
            return ResponseEntity.internalServerError().body("An error occurred.");
        }
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/add/{id}")
    public ResponseEntity<Object> createOrder(@RequestBody Order order, @PathVariable("id") String id) {
        System.out.println(order);
        try {

            Optional<Customer> customerOp = customerService.findById(Long.parseLong(id));
            Customer customer = customerOp.get();
            order.setCustomer(customer);
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(orderService.save(order)));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateOrder(@RequestBody Order order, @PathVariable("id") String id) {
        try {

            Optional<Customer> customerOp = customerService.findById(Long.parseLong(id));
            Customer customer = customerOp.get();
            order.setCustomer(customer);

            Order orderToUpdate = orderService.findById(order.getId());

          if(order.getLabel() != null) {
              orderToUpdate.setLabel(order.getLabel());
          }

          if(order.getStatus() != null) {
              orderToUpdate.setStatus(order.getStatus());
          }
          if(order.getType() != null) {
              orderToUpdate.setType(order.getType());
            }
          if(order.getNumberOfDay() != null) {
              orderToUpdate.setNumberOfDay(order.getNumberOfDay());
          }
          if(order.getUnitPrice() != null) {
              orderToUpdate.setUnitPrice(order.getUnitPrice());
          }

            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(orderService.save(order)));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString("Order deleted"));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }


}
