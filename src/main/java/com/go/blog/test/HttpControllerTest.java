package com.go.blog.test;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


// 클라이언트(사용자) 요청 -> 응답(HTML 파일)
// @Controller

// 클라이언트(사용자) 요청 -> 응답(Data)

@Log4j2
@RestController
public class HttpControllerTest {


   @PostMapping("/http/post")
   public String postTest(@RequestBody Member m){
        return "포스트요청: " + m.getId() + " " + m.getUsername() + " " + m.getPassword() + " " + m.getEmail();
   }


}
