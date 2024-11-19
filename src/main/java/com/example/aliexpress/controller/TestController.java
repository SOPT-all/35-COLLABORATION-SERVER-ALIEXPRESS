package com.example.aliexpress.controller;

import com.example.aliexpress.common.dto.ResponseDto;
import com.example.aliexpress.common.exception.BusinessException;
import com.example.aliexpress.common.message.BusinessErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test/default")
    public ResponseEntity<ResponseDto<Void>> testDefault() {
        throw new RuntimeException();
    }

    @GetMapping("/test/business")
    public ResponseEntity<ResponseDto<Void>> testBusiness() {
        throw new BusinessException(BusinessErrorMessage.BAD_REQUEST);
    }

    @GetMapping("/test/success")
    public ResponseEntity<ResponseDto<Void>> testSuccess() {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(null));
    }
}
