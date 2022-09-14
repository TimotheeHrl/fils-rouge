/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filRouge.filRouge.controller.serialiser.OrderSerializer;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author maxla
 */
@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@JsonSerialize(using = OrderSerializer.class)
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type;
    
    private String label;
    
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
    
    @PositiveOrZero(message = "doit etre superieur a 0!")
    private Long numberOfDay;
    
    @PositiveOrZero(message = "doit etre superieur a 0!")
    private Double unitPrice;
    
//    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
//  //  @Formula("unit_price*number_of_day")
//    private Double totalExcludeTaxe ;
//
//    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
////    @Formula("totalExcludeTaxe*1.20")
//    private Double totalWithTaxe ;
   
    
    private String status;

    public Order(String type, String label, Customer customer, Long numberOfDay, Double unitPrice, String status) {
        this.type = type;
        this.label = label;
        this.customer = customer;
        this.numberOfDay = numberOfDay;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public Double getTotalExcludeTaxe(){
        return this.unitPrice*numberOfDay;
    }
    
    public Double getTotalWithTaxe(){
        return this.getTotalExcludeTaxe()*1.20;
    }

    
}
