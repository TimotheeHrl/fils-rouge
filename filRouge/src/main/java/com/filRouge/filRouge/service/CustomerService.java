/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.AppUserRole;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.repository.CustomerRepository;
import com.filRouge.filRouge.exception.CustomException;
import com.filRouge.filRouge.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author maxla
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final   ICustomerService customerService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CustomerService (@Lazy  ICustomerService customerService, @Lazy CustomerRepository customerRepository, PasswordEncoder passwordEncoder
     , JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager){
        super();
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;

    }



    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean findByMail(String mail) {
        return false;
    }

    @Override
    public void deleteByMail(String mail) {

    }

    public String signin(String mail, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));
            return jwtTokenProvider.createToken(mail, customerRepository.findByMail(mail).getAppUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(Customer customer) {
        if (!customerService.findByMail(customer.getMail())) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
            return jwtTokenProvider.createToken(customer.getMail(), customer.getAppUserRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String mail) {
        customerService.deleteByMail(mail);
    }

    public Customer search(String mail) {
        Customer custumer = customerRepository.findByMail(mail);
        if (custumer == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return custumer;
    }

    public Customer whoami(HttpServletRequest req) {
        return customerRepository.findByMail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String mail) {
        return jwtTokenProvider.createToken(mail, customerRepository.findByMail(mail).getAppUserRoles());
    }


}
