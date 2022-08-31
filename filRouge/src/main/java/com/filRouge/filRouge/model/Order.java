/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenerationTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author maxla
 */
@Entity
@Table(name="orders")
@Data
@Setter
@Getter
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
    
    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
    @Formula("unitPrice*numberOfDay")
    private Double total_excludeTaxe;

    private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;


    @Autowired
    public  Order(String type, String label, Long numberOfDay, Double unitPrice, String status, Customer customer) {
        this.type = type;
        this.label = label;
        this.numberOfDay = numberOfDay;
        this.unitPrice = unitPrice;
        this.status = status;
        this.customer = customer;
    }

    public Order() {

    }
}
