package com.julio.learnSB.service;

import com.julio.learnSB.request.AccountRequest;
import com.julio.learnSB.request.JWTRequest;
import com.julio.learnSB.response.AccountResponse;
import com.julio.learnSB.response.JWTResponse;

public interface AccountService {
    AccountResponse save(AccountRequest request, String requester) throws Exception;

    JWTResponse login(JWTRequest request);
}
