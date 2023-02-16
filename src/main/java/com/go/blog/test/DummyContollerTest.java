package com.go.blog.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.blog.domain.RoleType;
import com.go.blog.domain.User;
import com.go.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController //html이 아니라 데이터를 리턴해주는 컨트롤러
public class DummyContollerTest {

    @Autowired // UserRepository 타입으로 스프링이 관리하고 있는 객체가 있다면 자동 주입 -> 의존성 주입(DI)
    private UserRepository userRepository;



    //user 삭제

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){


        try{
            userRepository.deleteById(id);

        }catch(EmptyResultDataAccessException e){//id 가 존재하지 않을 경우에 Exception -> 예외처리들에 대한 최고 부모함수 , 하지만 이러면 어디서 예외가 발생했는지 모르기 떄문에 정확한 예외를 걸어준다.
            return "삭제에 실패 , 해당 Id는 DB에 존재하지 않음";
        }
        return "삭제되었습니다. ";
    }




    //save 함수는 id 를 전달하지 않으면 insert를 해주고
    //id 를 전달할 경우 id가 있을 경우에는 update
    //id 를 전달했는데 id에 대한 데이터가 없으면 insert

    //email, password 수정
    //json 데이터를 받아오기 위해서는 @requestbody가 필요
    //더티 체킹
    @Transactional // 이 이노테이션을 사용하면 save하지 않아도 업데이트가 됨, 함수종료시에 자동 update,     // 영속화 시키고 나서 값을 변경하면 변경된 값을 감지해서 db에 수정을 날려줌
    @PutMapping("/dummy/user/{id}")
    public String updataeUser(@PathVariable int id, @RequestBody User requestuser) throws JsonProcessingException {
        //json 데이터로 요청 => java object 로 변환해서 받아줌 이것도 meesageConverter가 해줌
        System.out.println("id: " + id);
        System.out.println("password: " + requestuser.getPassword());
        System.out.println("email: " + requestuser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("수정에 실패했습니다. ");
        });

        user.setPassword(requestuser.getPassword());
        user.setEmail(requestuser.getEmail());
        //userRepository.save(user);

        //java object -> json 데이터로 변환 하기위해 객체 생성
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        System.out.println("user: " + user);

        return json;
    }



    //전체 select
    //전체 데이터 가져오기
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }


//    // paging 처리
//    // http://localhost:8080/dummy/user?page={페이지 번호}
//    //한페이지당 2건의 데이터 리턴받기
//    @GetMapping("/dummy/user")
//    public Page<User> pageList(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC)
//                                   Pageable pageable){
//
//        Page<User> user = userRepository.findAll(pageable);
//
//        return user;
//
//    }

    // paging 처리
    // http://localhost:8080/dummy/user?page={페이지 번호}
    //한페이지당 2건의 데이터 리턴받기
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC)
                               Pageable pageable){

        //페이징 객체 생성
        Page<User> pagingUser = userRepository.findAll(pageable);

        //페이징 객체 중 content만 가져오는 객체 생성
        List<User> user = pagingUser.getContent();

        return user;

    }



    //id 주소로 파라미터를 전달 받을 수있음

    //ex localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) throws Throwable {
        // user/4를 찾는데 DB에서 못찾아오게 되면 user가 null이 될텐데?
        // 그럼 return null이 될텐데 프로그램에 문제가 발생할텐데?
        // optional로 User 객체를 감싸서 가져올태니 null인지 아닌지 판단해라
        User user = userRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("해당 사용자는 없습니다!");
        });

//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
//            }
//        });

        //요청은 웹브라우저
        //user 객체 = 자바 오브젝트
        //변환 (웹브라우저가 이해할 수 있는 데이터) -> json
        //스프링 부트는 MessageConverter가 응답시에 자동 json 변환 작동
        //user 오브젝트를 json으로 변환해서 브라우저에 던저준다.
        return user;

    }


    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user){ //key = value (약속된 규칙)
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("username: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }






}
