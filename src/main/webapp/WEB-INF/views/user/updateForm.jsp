<%--
  Created by IntelliJ IDEA.
  User: gimsang-u
  Date: 2023/02/16
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>


<div class="container">
    <form>
      <!--어떤 user의 정보인지 userid값으로 확인하기 위해 hidden으로 id값을 서버에 가져옴-->
      <input type="hidden" id="id" value="${principal.user.id}"/>
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="username" class="form-control" value="${principal.user.username}" id="username" readonly>
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" value="${principal.user.password}" id="password">
      </div>

      <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" value="${principal.user.email}" id="email">
                  </div>
    </form>
      <button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>




