package org.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.server.encoders.ChatPayloadDecoder;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{chatId}/username/{username}",
        decoders = {ChatPayloadDecoder.class})
@ApplicationScoped
public class ChatSocket {
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("chatId") String chatId) {
        String sessionKey = username + chatId;
        sessions.put(sessionKey, session);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, @PathParam("chatId") String chatId) {
        String sessionKey = username + chatId;
        sessions.remove(sessionKey);
        broadcast(">> " + username + " left the chat", UUID.fromString(chatId));
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, @PathParam("chatId") String chatId, Throwable throwable) {
        System.out.println("##### ERROR #######");
        String sessionKey = username + chatId;
        sessions.remove(sessionKey);
        broadcast(">>" + username + " left the chat", UUID.fromString(chatId));
    }

    @OnMessage
    public void onMessage(ChatPayload chatPayload, @PathParam("username") String username, @PathParam("chatId") String chatId) {
        if (chatPayload.message.equalsIgnoreCase("_ready_")) {
            broadcast("User " + username + " joined the chat", chatPayload.chatId);
        } else {
            broadcast(username + ": " + chatPayload.message, chatPayload.chatId);
        }
    }

    private void broadcast(String message, UUID chatId) {
        sessions.entrySet().stream()
                .filter(item -> item.getKey().contains(chatId.toString())
                ).forEach(item -> item.getValue().getAsyncRemote().sendObject(message, result -> {
                            if (result.getException() != null) {
                                System.out.println("Unable to send message: " + result.getException());
                            }
                        }
                ));
    }
}