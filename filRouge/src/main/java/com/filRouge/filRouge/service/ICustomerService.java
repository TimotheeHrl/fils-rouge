/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.Customer;
import java.util.List;

/**
 *
 * @author maxla
 */
public interface ICustomerService {
    public List<Customer> findAll();

    public Customer findById(Long id);
    
    public void save(Customer customer);
    
    public void delete(Long id);
    
}
