package com.go.blog.repository;

import com.go.blog.domain.Board;
import com.go.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaRepository<User, Integer> -> User 테이블을 관리하는 리포지토리다. 그리고 User 테이블의  primarykey는 Integer 다.
//DAO
//빈으로 등록되냐는건 = 스프링 IOC에서 객체를 가지고 있냐고 물어보는것과 같다.
//자동으로 bean에 등록됨 = 스프링 IOC에 등록됨
// @Repository 생략 가능
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // Optional -> 네이밍 쿼리
    // Select * FROM user where username = 1?




}

//JPA Naming 전략
//SELECT * FROM user WHERE username = ?1 AND password = ?2
//User findByUsernameAndPassword(String username, String password);


//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
