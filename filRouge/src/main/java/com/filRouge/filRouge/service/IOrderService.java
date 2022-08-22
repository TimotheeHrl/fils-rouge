/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.Order;
import java.util.List;

/**
 *
 * @author maxla
 */
public interface IOrderService {
    
    public List<Order> findAll();

    public Order findById(Long id);
    
    public void save(Order order);
    
    public void delete(Long id);
    
}
