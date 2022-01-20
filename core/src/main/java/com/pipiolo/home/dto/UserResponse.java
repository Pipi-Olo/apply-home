package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

import java.util.Set;

public record UserResponse(
        String email,
        Boolean subscribed,
        Set<String> regions
) {

    public static UserResponse from(User user) {
        return UserResponse.of(
                user.getEmail(),
                user.getSubscribed(),
                user.getRegions()
        );
    }

    public static UserResponse of(
            String email,
            Boolean subscribed,
            Set<String> regions
    ) {
        return new UserResponse(
                email,
                subscribed,
                regions
        );
    }
}
