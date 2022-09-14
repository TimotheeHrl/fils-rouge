/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.controller.serialiser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.model.Order;
import java.io.IOException;

/**
 *
 * @author maxla
 */
public class CustomerSerializer extends StdSerializer<Customer> {

    public CustomerSerializer() {
        this(null);
    }
  
    public CustomerSerializer(Class<Customer> t) {
        super(t);
    }
    
    @Override
    public void serialize(Customer t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeNumberField("id", t.getId());
        jg.writeStringField("lastname", t.getLastname());
        jg.writeStringField("firstname", t.getFirstname());
        jg.writeStringField("company", t.getCompany());
        jg.writeStringField("mail", t.getMail());
        jg.writeStringField("phone", t.getPhone());
        jg.writeStringField("adress", t.getAdress());
        jg.writeStringField("zipCode", t.getZipCode());
        jg.writeStringField("city", t.getCity());
        jg.writeStringField("country", t.getCountry());
        jg.writeStringField("active", t.getActive().toString());
        jg.writeArrayFieldStart("orders");

            for (Order o : t.getOrders()) {
                jg.writeStartObject();
                jg.writeStringField("type", o.getType());
                jg.writeStringField("label", o.getLabel());
                jg.writeStringField("status", o.getStatus());
                jg.writeEndObject();
            }

        jg.writeEndArray();
        jg.writeEndObject();
    }
    
}
