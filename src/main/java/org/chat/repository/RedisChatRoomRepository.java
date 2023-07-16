package org.chat.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.chat.entity.Chat;

import java.util.UUID;

@ApplicationScoped
public class RedisChatRoomRepository implements  ChatRoomRepositoryI{
    @Override
    public void save(Chat chat) {

    }

    @Override
    public Chat get(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
