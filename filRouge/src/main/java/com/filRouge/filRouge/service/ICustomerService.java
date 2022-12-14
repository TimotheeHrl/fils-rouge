/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.Customer;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author maxla
 */
public interface ICustomerService {
    public List<Customer> findAll();


    Optional<Customer> findById(Long id);

    public Object save(Customer customer);
    
    public void delete(Long id);

    Customer findByMail(String mail);


    @Transactional
    void deleteByMail(String mail);


}
