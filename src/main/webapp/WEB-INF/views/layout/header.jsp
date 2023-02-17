<%--
  Created by IntelliJ IDEA.
  User: gimsang-u
  Date: 2023/02/16
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--승인태그 -->
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>게시판 만들기</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <c:choose>
        <c:when test="${empty sessionScope.principal}">
                <ul class="navbar-nav">
                          <li class="nav-item"><a class="nav-link" href="/loginForm">로그인</a></li>
                          <li class="nav-item"><a class="nav-link" href="/joinForm">회원가입</a></li>
                </ul>
        </c:when>
            <c:otherwise>
                 <ul class="navbar-nav">
                          <li class="nav-item"><a class="nav-link" href="/board/writeFrom">글쓰기</a></li>
                          <li class="nav-item"><a class="nav-link" href="/user/Form">회원정보</a></li>
                          <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                 </ul>
            </c:otherwise>
      </c:choose>
  </div>
</nav>
<br/>