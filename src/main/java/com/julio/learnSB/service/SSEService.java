package com.julio.learnSB.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SSEService {
    void sendEvents(SseEmitter emitter);
}
