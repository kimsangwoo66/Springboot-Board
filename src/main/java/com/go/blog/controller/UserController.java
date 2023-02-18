package com.go.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로   인증이 필요없는 곳에는 다 /auth 를 붙임
// /auth/** 허용
//그냥 주소가 / 이면 index.jsp로 허용
// static 이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {



    @GetMapping("/auth/joinForm")
    public String joinFrom(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){

        return "user/loginForm";
    }

}
