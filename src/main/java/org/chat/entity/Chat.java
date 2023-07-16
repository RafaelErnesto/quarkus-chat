package org.chat.entity;

import org.chat.enums.Status;

import java.util.UUID;

public class Chat {
    public UUID id = UUID.randomUUID();

    public String name;

    public Status status;

    public Password password;

    public Chat(String name, Status status, String pwd){
        this.name = name;
        this.status = status;
        this.password = new Password(pwd);
    }

    public Chat(String name){
        this.name = name;
        this.status = Status.PUBLIC;
    }
}
