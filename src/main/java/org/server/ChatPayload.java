package org.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ChatPayload {

    public String message;
    public  UUID chatId;

    public ChatPayload(@JsonProperty("message")String message, @JsonProperty("chatId") UUID chatId) {
        this.message = message;
        this.chatId = chatId;
    }
}
