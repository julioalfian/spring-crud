package com.julio.learnSB.repository;

import com.julio.learnSB.repository.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> getRefreshTokenByRefreshToken(String refreshToken);
}
