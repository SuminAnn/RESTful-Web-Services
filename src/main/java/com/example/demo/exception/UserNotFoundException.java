package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //상태코드 변경
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}// 없는 유저를 조회한 client의 문제이기 때문에 server문제인 500번대 예외는 적절하지 않기때문에 400번대 예외로 변경
