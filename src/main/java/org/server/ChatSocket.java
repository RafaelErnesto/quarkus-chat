package org.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.server.encoders.UUIDDecoder;
import org.server.encoders.UuidEncoder;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{chatId}/username/{username}",
        decoders = UUIDDecoder.class,
        encoders = UuidEncoder.class)
@ApplicationScoped
public class ChatSocket {
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("chatId") UUID chatId) {
        String sessionKey = username + chatId;
        sessions.put(sessionKey, session);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, @PathParam("chatId") UUID chatId) {
        String sessionKey = username + chatId;
        sessions.remove(sessionKey);
        broadcast(">> " + username + " left the chat", chatId);
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, @PathParam("chatId") UUID chatId, Throwable throwable) {
        String sessionKey = username + chatId;
        sessions.remove(sessionKey);
        broadcast(">>" + username + " left the chat", chatId);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username, @PathParam("chatId") UUID chatId) {
        if (message.equalsIgnoreCase("_ready_")) {
            broadcast("User " + username + " joined the chat", chatId);
        } else {
            broadcast(username + ": " + message, chatId);
        }
    }

    private void broadcast(String message, UUID chatId) {
        sessions.entrySet().stream()
                .filter(item -> item.getKey().contains(chatId.toString()))
                .forEach(item -> item.getValue().getAsyncRemote().sendObject(message, result -> {
                            if (result.getException() != null) {
                                System.out.println("Unable to send message: " + result.getException());
                            }
                        }
                ));
    }
}