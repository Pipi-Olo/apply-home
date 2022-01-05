package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

public record UserResponse(
        String email,
        Boolean subscribed
) {

    public static UserResponse from(User user) {
        return UserResponse.of(
                user.getEmail(),
                user.getSubscribed()
        );
    }

    public static UserResponse of(
            String email,
            Boolean subscribed
    ) {
        return new UserResponse(
                email,
                subscribed
        );
    }
}
