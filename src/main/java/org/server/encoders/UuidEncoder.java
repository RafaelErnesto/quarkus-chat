package org.server.encoders;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

import java.util.UUID;

public class UuidEncoder implements Encoder.Text<UUID>{
    @Override
    public String encode(UUID uuid) throws EncodeException {
        return uuid.toString();
    }
}
