package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

public record UserRequest(
        String email,
        Boolean isSubscribed
) {
    public User toEntity() {
        return User.builder()
                .email(email)
                .isSubscribed(isSubscribed)
                .build();
    }
}
