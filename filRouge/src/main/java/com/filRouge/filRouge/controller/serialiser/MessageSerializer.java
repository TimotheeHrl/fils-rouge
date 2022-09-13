package com.filRouge.filRouge.controller.serialiser;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.model.Message;
import org.springframework.core.serializer.Serializer;

import java.io.IOException;


public class MessageSerializer extends StdSerializer<Message> {

    public MessageSerializer() {
    this(null);
    }
    public MessageSerializer(Class<Message> t) {
        super(t);
    }


    @Override
    public void serialize(Message m, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeStringField("text", m.getText());
        jg.writeStringField("date", m.getLocalDateTime().toString());
        jg.writeObjectFieldStart("user");
        {
            jg.writeStringField("username", m.getUser().getUsername());
            jg.writeStringField("avatar", m.getUser().getAvatar());
        }
        jg.writeEndObject();
        jg.writeEndObject();
    }


}


