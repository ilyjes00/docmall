<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <style>

    </style>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/">DocMall</a></h5>
      <c:if test="${sessionScope.loginStatus != null }">
  <div style="width:100%; text-align:center;">
  <b>${sessionScope.loginStatus.mbsp_name }님</b>
  <b>${sessionScope.loginStatus.mbsp_email }</b>
  <b></b>
   <b>  최근접속시간  :<fmt:formatDate value="${sessionScope.loginStatus.mbsp_lastlogin}" pattern="yyyy-MM-dd hh:mm:ss"/></b>
  </div>
      </c:if>
      
  <nav class="my-2 my-md-0 mr-md-3">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <c:if test="${sessionScope.loginStatus == null }">
    <a class="p-2 text-dark" href="/member/login">Login</a>
    <a class="p-2 text-dark" href="/member/join">Join</a>

    </c:if>
</div>
    <!-- 로그인 이후 표시 -->
    <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-dark " href="/member/logout">Logout</a>
    <a class="p-2 text-dark" href="/member/confirmPw">Modify</a>
    
    </c:if> 

        <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-dark" href="/member/mypage">Mypage</a>
        </c:if> 
    <a class="p-2 text-dark" href="#">Pricing</a>
    <a class="p-2 text-dark" href="#">Cart</a>
  </nav>
  <%--a class="btn btn-outline-primary" href="#">Sign up</a> --%>
</div>