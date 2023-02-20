package com.go.blog.service;

import com.go.blog.domain.Board;
import com.go.blog.domain.User;
import com.go.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. 스프링 IOC 컨테이너에 등록 즉 IOC를 해줌
@Service
public class BoardService {

    @Autowired // 자동 의존성 주입(DI)
    private BoardRepository boardRepository;



    @Transactional // 하나의 이메소드 서비스 전체가 하나의 트랜잭션으로 묶이게됨. 따라서 전체가 성공하면 commit이됨 , 전체에서 실패가 뜨면 롤백
    public void boardSave(Board board, User user){ //받는게 title이랑 content, userid
            board.setCount(0);
            board.setUser(user);
            boardRepository.save(board);
    }

    //글 목록 조회
    //페이징 기능 추가
    @Transactional(readOnly = true)
    public Page<Board> boardsSelect(Pageable pageable){
        return boardRepository.findAll(pageable);
    }


    //글 상세 내용 조회
    @Transactional(readOnly = true)
    public Board boardDetail(int id){
        return boardRepository.findById(id).
                orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });

    }

    //글 삭제
    @Transactional
    public void boardDelete(int id){

        boardRepository.deleteById(id);

    }

    //글 수정
    @Transactional
    public void boardUpdate(int id, Board requestBoard){
        //영속화
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                   return new IllegalArgumentException("게시글을 찾는데 실패하였습니다.");
                });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수 종료시 즉 서비스가 종료될때 트랜잭션이 종료.. 이때 더티체킹 발생 -> DB에서 자동업데이트 = flush
        // 이렇게 하려면 어노테이션 transactional을 걸어야함
    }


}
