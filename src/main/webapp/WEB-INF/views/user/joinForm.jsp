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
    <form action="/user/join" method="post">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="username" class="form-control" placeholder="Enter Username" id="username">
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" placeholder="Enter Password" id="password">
      </div>

      <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" placeholder="Enter Email" id="email">
                  </div>
    </form>
      <button id="btn-save" class="btn btn-primary">회원가입완료</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>




