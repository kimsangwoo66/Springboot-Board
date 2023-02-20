package com.go.blog.controller;

import com.go.blog.config.auth.PrincipalDetail;
import com.go.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {


    @Autowired
    private BoardService boardService;

    // user 권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }


    ////컨트롤러에서 spring 시큐리티 세션을 어떻게 찾는지 -> @AuthenticationPrincipal PrincipalDetail principal로 찾을 수 있음
    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC)
    Pageable pageable){
        model.addAttribute("boards", boardService.boardsSelect(pageable));
        //System.out.println("로그인 사용자 아이디: " + principal.getUsername());
        // /WEB-INF/views/index.jsp

        return "index"; //veiwresolver가 작동 -> index페이지로 model의 정보를 가지고 이동
        // 모델에 담으면 viewResolver가 model을 view까지 이동 시켜줌
    }


    @GetMapping("/board/{id}")
    public String findByIdBoardDetail(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/detail";

    }


    @GetMapping("/board/{id}/updateForm")
    public String findByIdBoardUpdate(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.boardDetail(id));

        return "board/updateForm";
    }



}
