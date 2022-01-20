package com.pipiolo.home.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, "Ok"),

    BAD_REQUEST(10000, "Bad request"),
    SPRING_BAD_REQUEST(10001, "Spring-detected bad request"),
    VALIDATION_ERROR(10002, "Validation error"),
    NOT_FOUND(10003, "Requested resource is not found"),

    INTERNAL_ERROR(20000, "Internal error"),
    SPRING_INTERNAL_ERROR(20001, "Spring-detected internal error"),
    DATA_ACCESS_ERROR(20002, "Data access error")
    ;

    private final Integer code;
    private final String message;
}
