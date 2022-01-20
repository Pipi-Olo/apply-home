package com.pipiolo.home.dto;

import com.pipiolo.home.constant.ErrorCode;

public record ErrorResponse(
        ErrorCode errorCode,
        String errorMessage
) {
    public static ErrorResponse from(ErrorCode errorCode) {
        return ErrorResponse.of(errorCode, errorCode.getMessage());
    }

    private static ErrorResponse of(ErrorCode errorCode, String errorMessage) {
        return new ErrorResponse(errorCode, errorMessage);
    }
}
