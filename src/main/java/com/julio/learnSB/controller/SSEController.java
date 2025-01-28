package com.julio.learnSB.controller;

import com.julio.learnSB.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public interface SSEController {
    SseEmitter streamSSE() throws Exception;
}
