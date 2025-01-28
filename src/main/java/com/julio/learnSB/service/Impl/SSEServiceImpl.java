package com.julio.learnSB.service.Impl;

import com.julio.learnSB.response.SSEResponse;
import com.julio.learnSB.service.SSEService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SSEServiceImpl implements SSEService {

    @Async("callSSE")
    public CompletableFuture<Void> callPegasus(SseEmitter emitter) {
        try {

            Thread.sleep(2000);

            emitter.send(SSEResponse.builder().id(1l).to("istanbul").from("izmir").price(new BigDecimal(1000.20).setScale(2, RoundingMode.HALF_EVEN)).build());

            return CompletableFuture.completedFuture(null);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEvents(SseEmitter emitter) {
        CompletableFuture<Void> pegasus = callPegasus(emitter);
    }
}
