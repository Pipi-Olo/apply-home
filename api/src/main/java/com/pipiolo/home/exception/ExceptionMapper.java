package com.pipiolo.home.exception;

import com.pipiolo.home.constant.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class ExceptionMapper {
    public static HttpStatus mapTo(ErrorCode errorCode) {
        switch (errorCode) {
            case OK:
                return HttpStatus.OK;
            case BAD_REQUEST:
            case SPRING_BAD_REQUEST:
            case VALIDATION_ERROR:
            case NOT_FOUND:
                return HttpStatus.BAD_REQUEST;
            case INTERNAL_ERROR:
            case SPRING_INTERNAL_ERROR:
            case DATA_ACCESS_ERROR:
                return HttpStatus.INTERNAL_SERVER_ERROR;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
