<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
  <header class="main-header">

    <!-- Logo -->
    <a href="/admin/intro" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
           <li class="dropdown messages-menu">
           <a href="#">최근접속시간 : <fmt:formatDate value="${sessionScope.adminStatus.admin_visit_date }" pattern="yyyy-MM-dd hh:mm:ss"/></a>
           </li>
           </ul>
          <!-- Messages: style can be found in dropdown.less-->
          <c:if test="${sessionScope.adminStatus != null }">
          <ul class="nav navbar-nav">
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
			<a href="/admin/logout">로그아웃</a>
            </ul>
                      <ul class="nav navbar-nav">
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
			<a href="/">[쇼핑몰]</a>
            </ul>
			</c:if>
      </div>
    </nav>
  </header>