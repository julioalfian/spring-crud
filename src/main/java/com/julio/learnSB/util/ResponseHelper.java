package com.julio.learnSB.util;

import com.julio.learnSB.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static ResponseEntity<BaseResponse> buildOkResponse(Object data) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(data)
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }
    public static ResponseEntity<BaseResponse> buildBadRequestResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .success(false)
                .data(message).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(mainResponse);
    }
    public static ResponseEntity<BaseResponse> buildInternalServerErrorResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(message)
                .success(false).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mainResponse);
    }
}
