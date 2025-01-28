package com.julio.learnSB.controller;

import com.julio.learnSB.request.AccountRequest;
import com.julio.learnSB.request.JWTRequest;
import com.julio.learnSB.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public interface AccountController {

    @PostMapping
    ResponseEntity<BaseResponse> save(@RequestBody AccountRequest request) throws Exception;

    @PostMapping(value = {"/login"})
    ResponseEntity<BaseResponse> login(@RequestBody JWTRequest request) throws Exception;
}
