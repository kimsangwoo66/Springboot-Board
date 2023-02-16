//package com.go.blog.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//public class blogControllerTest {
//
//    @GetMapping("/blog/test")
//    public String blogControllerTest(){
//        return "/test.html";
//    }
//
//    @GetMapping("/blog/img")
//    public String imgcall(){
//        return "/img455.png";
//    }
//
//    //jsp 는 동적인 파일이기때문에 찾을 수 없다.
//    @GetMapping("/blog/testj1")
//    public String jspcall(){
//        return "/test.jsp";
//    }
//
//
//    //jsp 는 동적인 파일이기때문에 찾을 수 없다.
//    @GetMapping("/blog/testj2")
//    public String jspcall1(){
//        //application.properties에 prefix, suffix 추가
//        //prefix : /WEB-INF/views
//        //suffix : .jsp
//        //풀 경로 : /WEB-INF/views/test2.jsp
//        return "/test2";
//    }
//
//}
