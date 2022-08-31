/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.repository.CustomerRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author maxla
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final   ICustomerService customerService;



    @Autowired
    public CustomerService (@Lazy  ICustomerService customerService, @Lazy CustomerRepository customerRepository){
        super();
        this.customerService = customerService;
        this.customerRepository = customerRepository;

    }



    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByMail(String mail) {
        return customerRepository.findByMail(mail);

    }

    @Override
    public void deleteByMail(String mail) {

    }


}
