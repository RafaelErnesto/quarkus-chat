package org.chat.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.chat.dto.CreateChatRoomDto;
import org.chat.service.ChatRoomService;

import java.util.UUID;

@Path("/chat")
@ApplicationScoped
public class ChatResource {

    @Inject
    ChatRoomService chatRoomService;


    @POST()
    public UUID createChat(@Valid CreateChatRoomDto createChatRoomDto){
        return chatRoomService.create(createChatRoomDto);
    }
}
