//js도 정적인 파일
let index = {
    init: function() {
        $("#btn-save").on("click", ()=>{ //function을 사용하지 않는 이유는 this를 바인딩하기 위함
            this.save();
        });

        $("#btn-delete").on("click", ()=>{ //function을 사용하지 않는 이유는 this를 바인딩하기 위함
                    this.deleteById();
                });

    },


    save: function(){
        //alert("user의 save 함수가 호출됨");

        //입력 값을 찾아서 자바스큽트 오브젝트에 넣음 #-> #username 은 id=username 으로 되어있는 곳의 입력값을 찾음
           let data = {
                title: $("#title").val(),
                content: $("#content").val()
           };
           //console.log(data)

            //ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청
           //ajax 호출시 default가 비동기 호출
           //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
           $.ajax({
               type:"POST",
               url:"/api/board" ,
               data:JSON.stringify(data),//http body 데이터 //그냥 던지면 자바가 이해할 수없기 때문에 JSON.stringify에 data객체를 담아 전달
               contentType:"application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MINE)
               dataType:"json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열(생긴 Json이라면) => javascript object로 변경
           //회원가입 수행을 요청
           }).done(function(resp){
                alert("글쓰기 완료");
                location.href="/";
           }).fail(function(error){
                //회원가입에 실패하면 실행하는 함수
                alert(JSON.stringify(error));

           });
    },


    deleteById: function(){
               // id 값을 찾아서 거기 있는 텍스트값 ${board.id} 을 가져오려면 .val() 말고 .text()를 써야함
               var id = $("#id").text();


               $.ajax({
                   type:"DELETE",
                   url:"/api/board/"+id,
                   dataType:"json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열(생긴 Json이라면) => javascript object로 변경
               //회원가입 수행을 요청
               }).done(function(resp){
                    alert("삭제가 완료되었습니다.");
                    location.href="/";
               }).fail(function(error){
                    //회원가입에 실패하면 실행하는 함수
                    alert(JSON.stringify(error));

               });
        },

}

index.init();
