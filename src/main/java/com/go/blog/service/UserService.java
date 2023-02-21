package com.go.blog.service;

import com.go.blog.config.auth.PrincipalDetail;
import com.go.blog.domain.RoleType;
import com.go.blog.domain.User;
import com.go.blog.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. 스프링 IOC 컨테이너에 등록 즉 IOC를 해줌
@Service
public class UserService {

    @Autowired // 자동 의존성 주입(DI)
    private UserRepository userRepository;

    @Autowired // 자동 의존성 주입(DI)
    private BCryptPasswordEncoder encoder;



    Logger log;




    @Transactional // 하나의 이메소드 서비스 전체가 하나의 트랜잭션으로 묶이게됨. 따라서 전체가 성공하면 commit이됨 , 전체에서 실패가 뜨면 롤백
    public void userSave(User user){ //회원가입

            String rawPassword = user.getPassword(); // 비밀번호 원문
            String encPassword = encoder.encode(rawPassword); //해쉬 암호화

            user.setPassword(encPassword);
            user.setRole(RoleType.USER);
            userRepository.save(user);
    }


    // 여기서 받는 user는 외부로 받는 user (hidden을 통해 ajax로 id값을 받아왔기 때문)
    // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 후 영속화된 User 오브젝트를 수정
    // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주기 때문
    @Transactional
    public void userUpdate(User user, PrincipalDetail principal){

        //영속화
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });


        // 변경 패스워드 or 기존 패스워드
        String rawPassword = user.getPassword();


        // 가져온 password 값과 세션정보의 password 값이 같지 않을 경우
        if(rawPassword.equals(principal.getPassword())== false){

            //수정한 패스워드 재 암호화
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());

        }
        else{

            persistance.setPassword(rawPassword);
            persistance.setEmail(user.getEmail());

        }

        // 세선 정보 강제 변경 적용
        principal.setUser(user);

        // 회원 함수 종료시 = 서비스 종료 = 트랜젝션 종료 = commit이 자동으로 발생
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹되어 update문 실행
    }



}
