package org.chat;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.UUID;

@Path("/chat")
public class ChatResource {

    @POST()
    public String createChat(){
        return UUID.randomUUID().toString();
    }
}
