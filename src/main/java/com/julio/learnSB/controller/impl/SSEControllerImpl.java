package com.julio.learnSB.controller.impl;

import com.julio.learnSB.controller.SSEController;
import com.julio.learnSB.response.BaseResponse;
import com.julio.learnSB.service.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SSEControllerImpl implements SSEController {
    
    @Autowired
    private SSEService sseService;

    @Override
    public SseEmitter streamSSE() {

        SseEmitter emitter = new SseEmitter(5000l);

        sseService.sendEvents(emitter);

        return emitter;
    }
}
