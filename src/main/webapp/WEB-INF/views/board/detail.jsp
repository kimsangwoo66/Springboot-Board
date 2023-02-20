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
          <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

          <c:if test="${board.user.id == principal.user.id}">
            <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
            <button id="btn-delete" class="btn btn-danger">삭제</button>
          </c:if>

          <br/>

          <div>
            글 번호: <span id="id"><i>${board.id} </i></span>
            작성자: <span id="id"><i>${board.user.username} </i></span>
          </div>
          <br/>
          <br/>
          <div class="form-group">
            <label for="title">Title</label>
            <h3>${board.title}</h3>
          </div>

          <div class="form-group">
            <label for="content">Content:</label>
            <div>${board.content}</div>
          </div>

          <hr/>



</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>




