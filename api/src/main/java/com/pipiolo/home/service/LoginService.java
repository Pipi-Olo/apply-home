package com.pipiolo.home.service;

import com.pipiolo.home.domain.User;
import com.pipiolo.home.dto.SignupRequest;
import com.pipiolo.home.exception.GeneralException;
import com.pipiolo.home.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.pipiolo.home.constant.ErrorCode.BAD_REQUEST;
import static com.pipiolo.home.constant.Role.ROLE_USER;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(SignupRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new GeneralException(BAD_REQUEST, "Email Already Exists.");
        }

        return userRepository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(ROLE_USER)
                .subscribed(request.subscribed())
                .regions(request.regions())
                .build());
    }

    public void logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        new SecurityContextLogoutHandler().logout(
                request,
                response,
                SecurityContextHolder.getContext().getAuthentication()
        );
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}