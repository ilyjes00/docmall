<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <!-- Bootstrap CSS 링크 추가 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  </head>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a style="text-align: left;" class="navbar-brand" href="#">Never</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample08">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Centered nav only <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
    </ul>
  </div>
  <c:if test="${sessionScope.loginStatus == null }">
    <a class="p-2 text-white" href="/member/login">Login</a>
    <a class="p-2 text-white" href="/member/join">Join</a>
    </c:if>
    <!-- 로그인 이후 표시 -->
    <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-white " href="/member/logout">Logout</a>
    <a class="p-2 text-white" href="/member/confirmPw">Modify</a>
    
    </c:if> 

        <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-white" href="/member/mypage">Mypage</a>
    <a class="p-2 text-white" href="/admin/intro">[ADMIN]</a>
        </c:if> 
</nav>

</body>
</html>