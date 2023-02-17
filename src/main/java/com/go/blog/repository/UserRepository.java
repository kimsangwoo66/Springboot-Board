package com.go.blog.repository;

import com.go.blog.domain.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<User, Integer> -> User 테이블을 관리하는 리포지토리다. 그리고 User 테이블의  primarykey는 Integer 다.
//DAO
//빈으로 등록되냐는건 = 스프링 IOC에서 객체를 가지고 있냐고 물어보는것과 같다.
//자동으로 bean에 등록됨 = 스프링 IOC에 등록됨
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {


}