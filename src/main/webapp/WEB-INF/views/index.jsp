<%--
  Created by IntelliJ IDEA.
  User: gimsang-u
  Date: 2023/02/16
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="layout/header.jsp" %>


<div class="container">

<!--items 는 request 정보가 넘어올때 jstl을 통해서 받을 수 있음, 즉 index라는 페이지로 boards가 날라감-->
<!--board 라는 객체가 title이라는 변수를 갖고있음-->
<c:forEach var="board" items="${boards}">
    <div class="card m-2">
      <div class="card-body">
        <h4 class="card-title">${board.title}</h4>
        <a href="#" class="btn btn-primary">상세보기</a>
      </div>
    </div>
</c:forEach>



</div>

<%@ include file="layout/footer.jsp" %>




