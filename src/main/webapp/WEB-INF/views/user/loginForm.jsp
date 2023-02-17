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
    <form action="/action_page.php">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="username" class="form-control" placeholder="Enter Username" id="username">
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" placeholder="Enter Password" id="password">
      </div>

      <div class="form-group form-check">
        <label class="form-check-label">
          <input class="form-check-input" type="checkbox"> Remember me
        </label>
      </div>
      <button type="submit" class="btn btn-primary">로그인</button>
    </form>
</div>


<%@ include file="../layout/footer.jsp" %>



