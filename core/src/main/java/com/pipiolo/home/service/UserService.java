package com.pipiolo.home.service;

import com.pipiolo.home.domain.User;
import com.pipiolo.home.dto.UserRequest;
import com.pipiolo.home.dto.UserResponse;
import com.pipiolo.home.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse upsert(UserRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseGet(() -> request.toEntity());

        user.update(request);
        return UserResponse.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream().map(UserResponse::from).toList();
    }
}
