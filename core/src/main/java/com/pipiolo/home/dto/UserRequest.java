package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

public record UserRequest(
        String email,
        Boolean subscribed
) {
    public User toEntity() {
        return User.builder()
                .email(email)
                .subscribed(subscribed)
                .build();
    }
}
