/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.controller;

import com.filRouge.filRouge.model.Order;
import com.filRouge.filRouge.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 *
 * @author maxla
 */
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @RolesAllowed({"ROLE_MODERATEUR"})
    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @RolesAllowed({"ROLE_MODERATEUR"})
    @GetMapping("/customer/{id}")
    public List<Order> findByCustomer(@PathVariable Long id) {
        return orderService.findByCustomer(id);
    }

    @RolesAllowed({"ROLE_MODERATEUR"})
    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @RolesAllowed({"ROLE_MODERATEUR"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

}
