package com.julio.learnSB.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SSEResponse {
    private long id;
    private String to;
    private String from;
    private BigDecimal price;
}
