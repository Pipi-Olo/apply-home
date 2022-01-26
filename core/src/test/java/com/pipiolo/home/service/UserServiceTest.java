package com.pipiolo.home.service;

import com.pipiolo.home.dto.UserRequest;
import com.pipiolo.home.dto.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;

    public UserServiceTest(@Autowired UserService userService) {
        this.userService = userService;
    }

    @Test
    void test() {
        UserRequest request = new UserRequest("test@test.com", "password", Boolean.TRUE, null);
        UserResponse response = userService.upsert(request);

        Assertions.assertThat(response.email())
                .isEqualTo(request.email());
    }
}