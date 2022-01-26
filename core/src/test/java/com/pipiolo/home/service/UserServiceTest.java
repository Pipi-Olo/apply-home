package com.pipiolo.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;

    public UserServiceTest(@Autowired UserService userService) {
        this.userService = userService;
    }
}