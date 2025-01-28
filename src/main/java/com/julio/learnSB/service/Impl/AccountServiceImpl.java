package com.julio.learnSB.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julio.learnSB.constant.ConstantHeader;
import com.julio.learnSB.repository.AccountRepository;
import com.julio.learnSB.repository.entity.Account;
import com.julio.learnSB.request.AccountRequest;
import com.julio.learnSB.request.JWTRequest;
import com.julio.learnSB.response.AccountResponse;
import com.julio.learnSB.response.JWTResponse;
import com.julio.learnSB.service.AccountService;
import com.julio.learnSB.service.RefreshTokenService;
import com.julio.learnSB.util.MessageLib;
import com.julio.learnSB.util.exceptions.BadRequestException;
import com.julio.learnSB.util.exceptions.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.julio.learnSB.constant.SecurityConstant.*;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MessageLib message;


    RefreshTokenService refreshTokenService;


    private String getRandomPassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
    @Override
    public AccountResponse save(AccountRequest request, String requester) {
        Account requesterAccount = accountRepository.findByEmailAndActiveTrue(requester)
                .orElseThrow(() -> new NotAuthorizedException(message.getAccountNotFoundError()));

        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(message.getAccountSaveAccountEmailError());
        }
        String password = getRandomPassword();

        Account newAccount = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(password)
                .avatar(request.getAvatar())
                .build();
        newAccount.setCreatedDate(new Date());
        accountRepository.save(newAccount);
        return AccountResponse.builder()
                .email(newAccount.getEmail())
                .password(password)
                .build();
    }

    @Override
    public JWTResponse login(JWTRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BadRequestException(message.emailNotValid()));

        if (request.getPassword() != account.getPassword()) {
            throw new NotAuthorizedException(message.getLoginFailedError());
        }

        return createJwtResponse(account);
    }

    private JWTResponse createJwtResponse(Account account) {
        return createJwtResponse(account, false);
    }

    private JWTResponse createJwtResponse(Account account, Boolean isGoogleId) {

        String token = tokenCreate(account, isGoogleId);
        String refreshToken = refreshTokenService.createRefreshToken(account);

        JWTResponse response = JWTResponse.builder()
                .email(account.getEmail())
                .avatar(account.getAvatar())
                .name(account.getName())
                .JWTToken(token)
                .refreshToken(refreshToken)
                .accountId(account.getId())
                .build();

        return response;
    }

    private String tokenCreate(Account account, Boolean isGoogle) {
        Date date = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        if (isGoogle) {
            date = new Date(System.currentTimeMillis() + EXPIRATION_TIME_GOOGLE);
        }
        String x_ROLE = "", privilege = "";
        ObjectMapper objectMapper = new ObjectMapper();


        return JWT.create()
                .withClaim("email", account.getEmail())
                .withClaim(ConstantHeader.HEADER_X_ID, account.getId())
                .withClaim(ConstantHeader.HEADER_X_NAME, account.getName())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }
}
