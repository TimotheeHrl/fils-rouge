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
import java.util.List;

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
    public void serialize(Customer cu, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeNumberField("id", cu.getId());
        jg.writeStringField("lastName", cu.getLastname());
        jg.writeStringField("firstName", cu.getFirstname());
        jg.writeStringField("company", cu.getCompany());
        jg.writeStringField("mail", cu.getMail());
        jg.writeStringField("phone", cu.getPhone());
        jg.writeStringField("address", cu.getAdress());
        jg.writeStringField("active", cu.getActive().toString());
        jg.writeStringField("zipCode", cu.getZipCode());
        jg.writeStringField("city", cu.getCity());
        jg.writeStringField("country", cu.getCountry());

        jg.writeArrayFieldStart("orders");
        for (Order o : cu.getOrders()) {
            {
                jg.writeStartObject();
                jg.writeNumberField("id", o.getId());
                jg.writeStringField("type", o.getType());
                jg.writeStringField("label", o.getLabel());
                jg.writeStringField("status", o.getStatus());
                jg.writeEndObject();
            }
        }
        jg.writeEndArray();
        jg.writeEndObject();
    }
    
}
