package com.julio.learnSB.service;

import com.julio.learnSB.repository.entity.Account;

public interface RefreshTokenService {
    String createRefreshToken(Account account);

}
