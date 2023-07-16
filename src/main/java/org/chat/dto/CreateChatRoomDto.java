package org.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.chat.enums.Status;

import java.util.Optional;

public class CreateChatRoomDto {
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public Optional<Status> status;
    @JsonProperty("password")
    public Optional<String> password;

}
