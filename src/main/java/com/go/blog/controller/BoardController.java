package com.go.blog.controller;

import com.go.blog.config.auth.PrincipalDetail;
import com.go.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // user 권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }



    //@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping("/")      //컨트롤러에서 spring 시큐리티 세션을 어떻게 찾는지 -> @AuthenticationPrincipal PrincipalDetail principal로 찾을 수 있음
    public String index(){
        //System.out.println("로그인 사용자 아이디: " + principal.getUsername());


        // /WEB-INF/views/index.jsp

        return "index";
    }
}
