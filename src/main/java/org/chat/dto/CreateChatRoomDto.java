package org.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.chat.enums.Status;

import java.util.Optional;

public class CreateChatRoomDto {

    @NotBlank(message = "name cannot be blank")
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public Optional<Status> status;

    @Min(value = 8, message = "Password length must be at least 8 characters")
    @Max(value = 25, message = "Password length must be at most 25 characters")
    @JsonProperty("password")
    public Optional<String> password;

}
