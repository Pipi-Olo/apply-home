package com.pipiolo.home.controller.error;

import com.pipiolo.home.constant.ErrorCode;
import com.pipiolo.home.dto.ErrorResponse;
import com.pipiolo.home.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.pipiolo.home.constant.ErrorCode.*;

@RestControllerAdvice(annotations = {RestController.class})
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> general(GeneralException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.from(errorCode), errorCode.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> validation(ConstraintViolationException e) {
        return  new ResponseEntity<>(ErrorResponse.from(VALIDATION_ERROR), VALIDATION_ERROR.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        return  new ResponseEntity<>(ErrorResponse.from(INTERNAL_ERROR), INTERNAL_ERROR.getHttpStatus());
    }
}
