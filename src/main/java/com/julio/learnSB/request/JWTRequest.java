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
public class JWTRequest {
    @JsonProperty("google_id_token")
    private String googleIdToken;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firebase_token")
    private String firebaseToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
