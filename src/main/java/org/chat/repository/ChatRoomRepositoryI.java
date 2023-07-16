package org.chat.repository;

import org.chat.entity.Chat;

import java.util.UUID;

public interface ChatRoomRepositoryI {

    void save(Chat chat);

    Chat get(UUID id);

    void delete(UUID id);
}
