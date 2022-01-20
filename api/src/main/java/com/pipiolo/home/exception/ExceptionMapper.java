package com.pipiolo.home.exception;

import com.pipiolo.home.constant.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class ExceptionMapper {
    public static HttpStatus mapTo(ErrorCode errorCode) {
        return switch (errorCode) {
            case OK -> HttpStatus.OK;
            case BAD_REQUEST, SPRING_BAD_REQUEST, VALIDATION_ERROR, NOT_FOUND -> HttpStatus.BAD_REQUEST;
            case INTERNAL_ERROR, SPRING_INTERNAL_ERROR, DATA_ACCESS_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
