package com.julio.learnSB.util.exceptions;

public class BadRequestException extends BaseException{
    public BadRequestException(String data) {
        super("Bad request: " + data);
    }
    public BadRequestException(){
        this("Terdapat parameter kosong");
    }
}
