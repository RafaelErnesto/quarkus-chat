package org.server.encoders;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import org.server.ChatPayload;

import java.io.IOException;
import java.io.Reader;
import java.util.UUID;


@ApplicationScoped
public class ChatPayloadDecoder implements  Decoder, Decoder.Text<ChatPayload>, Decoder.TextStream<ChatPayload> {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public ChatPayload decode(String s) throws DecodeException {
        return objectMapper.convertValue(s, ChatPayload.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public ChatPayload decode(Reader reader) throws DecodeException, IOException {
        return  objectMapper.convertValue(reader.toString(), ChatPayload.class);
    }
}
