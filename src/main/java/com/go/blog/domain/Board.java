package com.go.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

// ORM 이다 == 엔터티 클래스이다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {


    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;


    @Lob // 내용은 데이터가 엄청나게 많아지기 때문에 Lob을 사용
    private String content; //섬머노트 라이브러리 <html태그가 섞여서 디자인>

    @ColumnDefault("0") //기본값
    private int count; // 조회수

    //이 필드는 원래 쿼리라면 user와 board를 조인해서 사용해야한다.
    //DB는 오브젝트를 저장할 수 없다.
    //자바는 오브젝트로 저장이 가능


    //FetchType.EAGER : 무조건 join 해서 들고오는 전략
    //너가 board 타입을 select하면 유저 정보는 무조건 가져올께 왜? 한건밖에 없으니까
    @ManyToOne(fetch = FetchType.EAGER) //연간관계 Many = Board, One = User -> 한명의 유저는 여러개의 게시물을 사용할 수있음, 여러개의 개시물은 한명에 의해 쓰일수 있음
    @JoinColumn(name="userId") //userId로 필드값이 생김 (FK)
    private User user; //게시물을 작성한 유저



    //mappedBy를 사용하면 연관관계의 주인이 아니다(이건 FK가 아니예요 란 의미) -> reply는 테이블에 생성되는 FK가 아니라는 의미
    //즉 fk주인은 reply테이블의 board 필드를 의미

    //fetch = FetchType.LAZY: 필요하면 join해서 들고오고 필요없으면 안들고오는 전략
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY) //하나의 게시물에서는 여러개의 댓글을 갖고있다.
    //@JoinColumn(name="replyId") -> 이건 필요없음, 사용하면 1정규화 규칙(원자성)이  깨지게됨
    // List<Reply>를 한 이유: 하나의 게시물에 여러개의 답글을 가져오기 떄문에
    private List<Reply> reply;



    @CreationTimestamp //데이터가 insert될때 update될때 자동으로 값이 들어감
    private Timestamp createDate;




}
