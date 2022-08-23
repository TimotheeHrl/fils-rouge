/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.filRouge.filRouge.repository;

import com.filRouge.filRouge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 *
 * @author maxla
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByMail(String mail);

    Customer findByMail(String mail);
    @Transactional
    void deleteByMail(String mail);


}
