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


    @Transactional(readOnly = true)
    public Board boardDetail(int id){
        return boardRepository.findById(id).
                orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });

    }

    @Transactional
    public void boardDelete(int id){

        boardRepository.deleteById(id);

    }


}
