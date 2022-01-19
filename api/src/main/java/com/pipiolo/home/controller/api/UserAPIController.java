package com.pipiolo.home.controller.api;

import com.pipiolo.home.dto.UserDeleteRequest;
import com.pipiolo.home.dto.UserRequest;
import com.pipiolo.home.dto.UserResponse;
import com.pipiolo.home.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserAPIController {

    private final UserService userService;

    @PostMapping
    public void upsert(UserRequest request) {
        userService.upsert(request);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping
    public void deleteUser(@Valid UserDeleteRequest request) {
        userService.deleteUser(request.email());
    }
}
