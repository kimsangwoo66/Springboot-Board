package com.go.blog.config.auth;

import com.go.blog.domain.User;
import com.go.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service //IOC
public class PrincipalDetailService implements UserDetailsService {


    @Autowired //DI
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌때 username과 password 변수 2개를 가로챔
    // 패스워드 부분 처리를 알아서 해줌
    // username이 DB에 있는지만 확인해 주면됨

    //그 확인을 이 함수에서 처리
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User principal = userRepository.findByUsername(username) //DB에서 username 이 있는지 찾음
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: " + username);

                });

        return new PrincipalDetail(principal); //시큐리티 세션에 user정보가 저장됨


    }


}
