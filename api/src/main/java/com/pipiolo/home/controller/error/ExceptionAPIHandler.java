package com.pipiolo.home.controller.error;

import com.pipiolo.home.constant.ErrorCode;
import com.pipiolo.home.dto.ErrorResponse;
import com.pipiolo.home.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static com.pipiolo.home.constant.ErrorCode.INTERNAL_ERROR;
import static com.pipiolo.home.constant.ErrorCode.VALIDATION_ERROR;
import static com.pipiolo.home.exception.ExceptionMapper.mapTo;

@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAPIHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> general(GeneralException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.from(errorCode), mapTo(errorCode));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> validation(ConstraintViolationException ex) {
        return  new ResponseEntity<>(ErrorResponse.from(VALIDATION_ERROR), mapTo(VALIDATION_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        return  new ResponseEntity<>(ErrorResponse.from(INTERNAL_ERROR), mapTo(INTERNAL_ERROR));
    }
}
