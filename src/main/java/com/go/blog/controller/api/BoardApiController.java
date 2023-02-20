package com.go.blog.controller.api;


import com.go.blog.config.auth.PrincipalDetail;
import com.go.blog.domain.Board;
import com.go.blog.dto.ResponseDto;
import com.go.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.boardSave(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);

    }
}
