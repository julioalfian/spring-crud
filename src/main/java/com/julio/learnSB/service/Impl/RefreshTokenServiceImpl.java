package com.julio.learnSB.service.Impl;

import com.julio.learnSB.repository.RefreshTokenRepository;
import com.julio.learnSB.repository.entity.Account;
import com.julio.learnSB.repository.entity.RefreshToken;
import com.julio.learnSB.service.RefreshTokenService;
import com.julio.learnSB.util.EntityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.julio.learnSB.constant.SecurityConstant;

import java.time.Instant;
import java.util.UUID;

public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String createRefreshToken(Account account) {
        String refreshTokenString = passwordEncoder.encode(UUID.randomUUID().toString());
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(refreshTokenString)
                .expiryDate(Instant.now().plusMillis(SecurityConstant.REFRESH_TOKEN_EXPIRATION_TIME))
                .account(account)
                .build();
        EntityUtil.created(refreshToken, account.getId());
        this.refreshTokenRepository.save(refreshToken);
        return refreshTokenString;
    }
}
