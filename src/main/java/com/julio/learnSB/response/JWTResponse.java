package com.julio.learnSB.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
    @JsonProperty("jwt_token")
    private String JWTToken;

    @JsonProperty("email")
    private String email;

    @JsonProperty("avatar")
    private String avatar;


    @JsonProperty("name")
    private String name;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("refresh_token")
    private String refreshToken;

}
