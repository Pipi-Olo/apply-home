package com.pipiolo.home.exception;

import com.pipiolo.home.constant.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pipiolo.home.constant.ErrorCode.BAD_REQUEST;

@DisplayName("[CORE][Exception]")
class GeneralExceptionTest {

    @DisplayName("예외 생성시 메시지 및 에러코드를 정상적으로 반환한다.")
    @Test
    void givenException_whenInstantiating_thenContainsRelevantInformation() {
        // Given
        Throwable t = new GeneralException(BAD_REQUEST);
        ErrorCode expectedErrorCode = BAD_REQUEST;
        String expectedMessage = BAD_REQUEST.getMessage();

        // When & Then
        Assertions.assertThat(t)
                .isInstanceOf(t.getClass())
                .hasMessage(expectedMessage)
                .hasFieldOrPropertyWithValue("errorCode", expectedErrorCode);
    }
}