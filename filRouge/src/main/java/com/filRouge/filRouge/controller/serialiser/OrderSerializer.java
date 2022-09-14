package com.filRouge.filRouge.controller.serialiser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.filRouge.filRouge.model.Order;
import java.io.IOException;
import java.math.BigDecimal;


public class OrderSerializer extends StdSerializer<Order> {

     public OrderSerializer() {
        this(null);
    }
  
    public OrderSerializer(Class<Order> t) {
        super(t);
    }

    @Override
    public void serialize(Order t, JsonGenerator jg, SerializerProvider sp) throws IOException {

            jg.writeStartObject();
            jg.writeNumberField("id", t.getId());
            jg.writeStringField("type", t.getType());
            jg.writeStringField("label", t.getLabel());
            jg.writeNumberField("numberOfDay", t.getNumberOfDay());
            jg.writeNumberField("unitPrice", t.getUnitPrice());
            jg.writeNumberField("totalExcludeTaxe", t.getTotalExcludeTaxe());
            jg.writeNumberField("totalwithTaxe", t.getTotalWithTaxe());
            jg.writeStringField("status", t.getStatus().toString());
            jg.writeObjectFieldStart("customer");
            {
                jg.writeNumberField("id", t.getCustomer().getId());
                jg.writeStringField("firstname", t.getCustomer().getFirstname());
                jg.writeStringField("lastname", t.getCustomer().getLastname());
                jg.writeStringField("company", t.getCustomer().getCompany());
                
            }
            jg.writeEndObject();
            jg.writeEndObject();


    }


}
