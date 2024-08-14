package com.discwords.discwords.deserializer;


import com.discwords.discwords.model.DirectMessageDTO;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DirectMessageDeserializer extends StdDeserializer<DirectMessageDTO> {
    protected DirectMessageDeserializer(Class<DirectMessageDTO> vc){
        super(vc);
    }

    public DirectMessageDeserializer(){
        this(null);
    }

    @Override
    public DirectMessageDTO deserialize(JsonParser p, DeserializationContext context) throws IOException, JacksonException{
        JsonNode node = p.getCodec().readTree(p);


        DirectMessageDTO directMessageDTO = new DirectMessageDTO();
        directMessageDTO.setConversation_id((long)node.get("conversation_id").asLong(0));
        directMessageDTO.setProfile_id((long)node.get("profile_id").asLong(0));
        directMessageDTO.setMessage((String)node.get("message").asText());


        return directMessageDTO;
    }
}