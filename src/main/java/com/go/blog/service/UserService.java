package com.go.blog.service;

import com.go.blog.domain.User;
import com.go.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. 스프링 IOC 컨테이너에 등록 즉 IOC를 해줌
@Service
public class UserService {

    @Autowired // 자동 의존성 주입(DI)
    private UserRepository userRepository;

    @Transactional // 하나의 이메소드 서비스 전체가 하나의 트랜잭션으로 묶이게됨. 따라서 전체가 성공하면 commit이됨 , 전체에서 실패가 뜨면 롤백
    public void userSave(User user){ //회원가입
            userRepository.save(user);
    }



}
