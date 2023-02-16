package com.go.blog.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;


@Data //getter setter 생성
@NoArgsConstructor // 빈생성자 생성
@AllArgsConstructor// 전체 생성자 생성
@Entity //User 클래스가 mysql에 생성
@Builder // 빌더패턴!! -> 공부하기
//@DynamicInsert //insert 할떄 null 인 필드 값은 제외
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 전략을 따라감
    private int id; // 자동 증가 auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'")
    //DB는 RoleType 이란게 없음
    //따라서 DB가 인식할 수 있는 타입으로 변경해줘야함 -> Enumerated 사용
    @Enumerated(EnumType.STRING)
    private RoleType role; //enum을 사용하는게 좋음 //ADMIN,USER 만 사용 할 수 있게 타입이 강제가 됨

    @CreationTimestamp //시간이 자동으로 입력
    private Timestamp createDate;


}
