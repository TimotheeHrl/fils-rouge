/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author maxla
 */
@Service
public class CustomerService implements ICustomerService{

    private CustomerRepository customRepo; 

    public CustomerService(CustomerRepository customRepo) {
        this.customRepo = customRepo;
    }

    
    @Override
    public List<Customer> findAll() {
        return customRepo.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customRepo.save(customer);
    }

    @Override
    public void delete(Long id) {
        customRepo.deleteById(id);
    }
    
}
