/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filRouge.filRouge.controller.serialiser.OrderSerializer;
import lombok.*;


/**
 *
 * @author maxla
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
@JsonSerialize(using = OrderSerializer.class)
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type;
    
    private String label;
    

    
    @PositiveOrZero(message = "doit etre superieur a 0!")
    private Long numberOfDay;
    
    @PositiveOrZero(message = "doit etre superieur a 0!")
    private Double unitPrice;
    


    private String status;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;


}
