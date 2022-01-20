package com.pipiolo.home.dto;

import javax.validation.constraints.NotBlank;

public record UserDeleteRequest(
        @NotBlank String email
) {
}
