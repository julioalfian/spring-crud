package com.julio.learnSB.controller.impl;

import com.julio.learnSB.constant.ConstantHeader;
import com.julio.learnSB.controller.AccountController;
import com.julio.learnSB.request.AccountRequest;
import com.julio.learnSB.request.JWTRequest;
import com.julio.learnSB.response.BaseResponse;
import com.julio.learnSB.response.JWTResponse;
import com.julio.learnSB.service.AccountService;
import com.julio.learnSB.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AccountControllerImpl implements AccountController {
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;
    @Override
    public ResponseEntity<BaseResponse> save(AccountRequest request) throws Exception {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_ID).toString();
        return ResponseHelper.buildOkResponse(accountService.save(request, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> login(JWTRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        JWTResponse response = accountService.login(request);

        baseResponse.setData(response);
        baseResponse.setSuccess(true);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
