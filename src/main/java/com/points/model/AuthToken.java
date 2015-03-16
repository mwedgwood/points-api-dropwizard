package com.points.model;

import java.util.UUID;

public class AuthToken {

    private final String token;

    public AuthToken() {
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }
}
