package com.go.blog.controller.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.blog.domain.RoleType;
import com.go.blog.domain.User;
import com.go.blog.dto.ResponseDto;
import com.go.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController //데이터만 return 용도
public class UserApiController {

    @Autowired //service도 DI 가능 왜? spring IOC 컨테이너에 userservice를 등록시켰기 때문
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        //실제로 DB에 insert 후 아래로 return
        user.setRole(RoleType.USER);
        userService.userSave(user);

        //java object -> json 데이터로 변환 하기위해 객체 생성

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바 오브젝트를 Json으로 변환해서 리턴(Jackson),1이면 성공 실패면 -1
    }
       //구 로그인 API
//    @PostMapping("/api/user/login")
//    public String login(@RequestBody User user, HttpSession session) throws JsonProcessingException {
//        System.out.println("userApiController: login 호출됨");
//        User principal = userService.userLogin(user); // 접근 주체
//
//        if (principal !=null){
//            session.setAttribute("principal", principal);
//        }
//        //return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(principal);
//        return json;
//    }



}
