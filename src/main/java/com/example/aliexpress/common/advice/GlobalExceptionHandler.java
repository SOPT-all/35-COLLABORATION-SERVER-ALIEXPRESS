package com.example.aliexpress.common.advice;

import com.example.aliexpress.common.dto.ResponseDto;
import com.example.aliexpress.common.exception.BusinessException;
import com.example.aliexpress.common.message.BusinessErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ResponseDto<BusinessErrorMessage>> handleBusinessException(BusinessException e) {
        return ResponseEntity
                .status(e.getErrorMessage().getHttpStatus())
                .body(ResponseDto.fail(e.getErrorMessage()));
    }

    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseDto<BusinessErrorMessage>> handleException(Exception e) {
        // 예외 메시지와 스택 트레이스를 로그에 기록
        logger.error("Unhandled exception occurred: {}", e.getMessage(), e);
        return ResponseEntity
                .status(BusinessErrorMessage.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ResponseDto.fail(BusinessErrorMessage.INTERNAL_SERVER_ERROR));
    }
}
