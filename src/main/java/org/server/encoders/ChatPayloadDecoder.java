package org.server.encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import org.server.ChatPayload;

@ApplicationScoped
public class ChatPayloadDecoder implements Decoder, Decoder.Text<ChatPayload> {

    @Override
    public ChatPayload decode(String s) throws DecodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, ChatPayload.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

}
