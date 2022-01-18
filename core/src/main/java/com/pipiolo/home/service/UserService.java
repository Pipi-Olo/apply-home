package com.pipiolo.home.service;

import com.pipiolo.home.domain.User;
import com.pipiolo.home.dto.UserRequest;
import com.pipiolo.home.dto.UserResponse;
import com.pipiolo.home.exception.GeneralException;
import com.pipiolo.home.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pipiolo.home.constant.ErrorCode.BAD_REQUEST;
import static com.pipiolo.home.constant.ErrorCode.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void insert(UserRequest request) {
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new GeneralException(BAD_REQUEST, "Email Already Exists");
                });

        userRepository.save(request.toEntity());
    }

    @Transactional
    public UserResponse update(UserRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(NOT_FOUND, "There is no Email" + request.email()));

        user.update(request);
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse upsert(UserRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseGet(request::toEntity);

        user.update(request);
        return UserResponse.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream().map(UserResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        return UserResponse.from(userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(NOT_FOUND, "There is no id=" + id)));
    }


    @Transactional(readOnly = true)
    public List<UserResponse> getUsersContainsRegion(String region) {
        return userRepository.findByRegionsAndSubscribedIsTrue(region)
                .stream().map(UserResponse::from).toList();
    }
}
