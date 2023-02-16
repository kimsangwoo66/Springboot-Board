package com.go.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //어디에서든 예외가 발생하면 이 클래스를 실행하기 위해 단 어노테이션
@RestController
public class globalExceptionHandler {


    //IllegalArgumentException 예외가 터질경우 스프링이 이함수에 받아오고 메소드 실행
    @ExceptionHandler(value=IllegalArgumentException.class)
     public String handlerArgumentException(IllegalArgumentException e){

         return "<h1>" + e.getMessage() + "</h1>";

     }

}
