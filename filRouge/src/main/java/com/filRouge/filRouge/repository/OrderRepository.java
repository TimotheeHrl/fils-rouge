/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.filRouge.filRouge.repository;

import com.filRouge.filRouge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author maxla
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByCustomer(Long id);

    public  List<Order> findByStatus( String status);

    @Transactional
    public  void deleteByCustomer(Long id);

    @Transactional
    public  void deleteByStatus(String status);


    @Transactional
    public  void deleteById(Long id);


}
