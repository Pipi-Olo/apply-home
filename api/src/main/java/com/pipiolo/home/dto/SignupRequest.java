package com.pipiolo.home.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public record SignupRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotNull Boolean subscribed,
        @Nullable Set<String> regions
) {
}