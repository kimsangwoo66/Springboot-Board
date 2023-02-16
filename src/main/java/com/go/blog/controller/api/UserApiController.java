package com.go.blog.controller.api;


import com.go.blog.domain.User;
import com.go.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //데이터만 return 용도
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        //실제로 DB에 insert 후 아래로 return
        return new ResponseDto<Integer>(HttpStatus.OK, 1); //자바 오브젝트를 Json으로 변환해서 리턴(Jackson)
    }
}
