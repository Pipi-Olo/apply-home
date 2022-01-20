package com.pipiolo.home.repository;

import com.pipiolo.home.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRegionsAndSubscribedIsTrue(String region);
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
}
