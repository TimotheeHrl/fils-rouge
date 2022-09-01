package com.filRouge.filRouge.controller.serialiser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.filRouge.filRouge.model.Order;

import java.io.IOException;

public class OrderSerializer extends StdSerializer<Order> {

        public OrderSerializer() {
            this(null);
        }

        public OrderSerializer(Class<Order> t) {
            super(t);
        }

        @Override
        public void serialize(Order o, JsonGenerator jg, SerializerProvider sp) throws IOException {
            jg.writeStartObject();
            jg.writeNumberField("id", o.getId());
            jg.writeStringField("type", o.getType());
            jg.writeStringField("label", o.getLabel());
            jg.writeStringField("status", o.getStatus());
            jg.writeStringField("numberOfDay", String.valueOf(o.getNumberOfDay()));
            jg.writeStringField("unitPrice", String.valueOf(o.getUnitPrice()));
            jg.writeObjectFieldStart("customer");
            {
                jg.writeNumberField("id", o.getCustomer().getId());
                jg.writeStringField("firstName", o.getCustomer().getFirstname());
                jg.writeStringField("lastName", o.getCustomer().getLastname());
                jg.writeStringField("company", o.getCustomer().getCompany());

            }
            jg.writeEndObject();
            jg.writeEndObject();
        }

}
