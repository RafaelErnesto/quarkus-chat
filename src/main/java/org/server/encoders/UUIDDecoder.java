package org.server.encoders;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

import java.util.UUID;

public class UUIDDecoder implements  Decoder, Decoder.Text<UUID> {
    @Override
    public UUID decode(String s) throws DecodeException {
        return UUID.fromString(s);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

}
