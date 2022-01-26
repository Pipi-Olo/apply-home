package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

import java.util.Set;

public record UserRequest(
        String email,
        String password,
        Boolean subscribed,
        Set<String> regions
) {
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .subscribed(subscribed)
                .regions(regions)
                .build();
    }

    public static UserRequest of(
            String email,
            String password,
            Boolean subscribed,
            Set<String> regions
    ) {
        return new UserRequest(
                email,
                password,
                subscribed,
                regions
        );
    }
}
