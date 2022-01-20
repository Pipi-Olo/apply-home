package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

import java.util.Set;

public record UserRequest(
        String email,
        Boolean subscribed,
        Set<String> regions
) {
    public User toEntity() {
        return User.builder()
                .email(email)
                .subscribed(subscribed)
                .regions(regions)
                .build();
    }
}
