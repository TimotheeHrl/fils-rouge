/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filRouge.filRouge.controller.serialiser.CustomerSerializer;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author maxla
 */
@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize(using = CustomerSerializer.class)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=50)
    private String lastname;
    
    @Column(length=50)
    private String firstname;
    
    @Column(length=50)
    private String company;
    
    @Email
    @Column(unique=true)
    private String mail;

    @Column(length=10)
    private String phone;
    
    @Column(length=200)
    private String adress;
    
    @Column(length=10)
    private String zipCode;
    
    @Column(length=100)
    private String city;
    
    @Column(length=50)
    private String country;
    
    private Boolean active;


    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;


    @Column(nullable=false)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;


    @Autowired
    public Customer(String lastname, String firstname, String company, String mail, String phone, String adress, String zipCode, String city, String country, Boolean active, String password,List<Order> orders) {
         this.lastname = lastname;
          this.firstname = firstname;
          this.company = company;
          this.mail = mail;
          this.phone = phone;
          this.adress = adress;
          this.zipCode = zipCode;
          this.city = city;
          this.country = country;
          this.active = active;
          this.orders = orders;
     }





}
