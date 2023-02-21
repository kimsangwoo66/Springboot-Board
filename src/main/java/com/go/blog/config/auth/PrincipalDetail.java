package com.go.blog.config.auth;

import com.go.blog.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인 진행을 완료하면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장

//@Getter //다른 레이어 세션에서 user 객체 내용을 꺼내 쓰기 위해
@Data
public class PrincipalDetail implements UserDetails {

    public PrincipalDetail(User user){

        this.user = user;
    }

    private User user; //PrincipalDetail 는 User 객체를 들고있다를 컴포지션이라고함

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴 (true: 만료안됨, false: 만료됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지에 대해 리턴(true: 안잠김, false: 잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴 (true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화(사용 가능)인지 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }


    // 계정이 갖고있는 권한 목록을 리턴(권한이 여러개 일수 있지만 우선은 한개)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(()->{return "ROLE_" + user.getRole();});

        return collectors;
    }
}
