package org.chat.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.chat.entity.Chat;

import java.util.HashMap;
import java.util.UUID;

@ApplicationScoped
public class RedisChatRoomRepository implements  ChatRoomRepositoryI{
    private final HashMap<String, Chat>  chats = new HashMap<>();
    @Override
    public void save(Chat chat) {
        this.chats.put(chat.id.toString(), chat);
    }

    @Override
    public Chat get(UUID id) {
        return this.chats.get(id.toString());
    }

    @Override
    public void delete(UUID id) {

    }
}
