package com.pipiolo.home.dto;

import com.pipiolo.home.constant.Role;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public record SignupRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotNull Boolean subscribed,
        @NotNull Role role,
        @Nullable Set<String> regions
) {
}