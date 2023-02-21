package com.go.blog.controller.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.blog.config.auth.PrincipalDetail;
import com.go.blog.domain.RoleType;
import com.go.blog.domain.User;
import com.go.blog.dto.ResponseDto;
import com.go.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController //데이터만 return 용도
public class UserApiController {

    @Autowired //service도 DI 가능 왜? spring IOC 컨테이너에 userservice를 등록시켰기 때문
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){ //회원가입 맵핑
        //실제로 DB에 insert 후 아래로 return


        userService.userSave(user);

        //java object -> json 데이터로 변환 하기위해 객체 생성

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바 오브젝트를 Json으로 변환해서 리턴(Jackson),1이면 성공 실패면 -1
    }


    //@AuthenticationPrincipal PrincipalDetail principal -> 스프링 시큐리티에서 세션값을 가져옴
    @PutMapping("/user")
    public ResponseDto<Integer> userInfoUpdate(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principal){

        //회원정보가 수정됬을때 세션값 변경 필요.. 그래야 클라이언트 단에서 회원정보 변경후 다시 들어갔을때 변경된값을 볼수 있음
        userService.userUpdate(user,principal);




        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }




}
