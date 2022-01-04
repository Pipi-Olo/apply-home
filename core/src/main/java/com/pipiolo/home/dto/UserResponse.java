package com.pipiolo.home.dto;

import com.pipiolo.home.domain.User;

public record UserResponse(
        String email,
        Boolean isSubscribed
) {

    public static UserResponse from(User user) {
        return UserResponse.of(
                user.getEmail(),
                user.getIsSubscribed()
        );
    }

    public static UserResponse of(
            String email,
            Boolean isSubscribed
    ) {
        return new UserResponse(
                email,
                isSubscribed
        );
    }
}
