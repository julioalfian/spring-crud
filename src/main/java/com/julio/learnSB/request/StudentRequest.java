package com.julio.learnSB.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String address;
}
