package com.go.blog.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


//댓글 테이블
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;


    //FK 지정
    @ManyToOne // 여러개의 댓글들은 하나의 게시물에 들어갈 수 있다.
    @JoinColumn(name = "boardId") //Reply 테이블의 boardId 필드는 Board 테이블에 기본키를 참조하게 생성 FK
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId") //Reply 테이블의 userId 필드는 User 테이블에 기본키를 참조하게 생성 FK
    private User user;


    @CreationTimestamp
    private Timestamp createDates;


}
