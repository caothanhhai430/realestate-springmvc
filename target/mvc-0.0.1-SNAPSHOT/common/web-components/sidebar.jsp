<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<nav id="sidebar">
  <div class="custom-menu">
  </div>
  <div class="img bg-wrap text-center py-4" style="background-image: url(/common/template/sidebar/images/bg_1.jpg);">
    <div class="user-logo">
      <div class="img" style="background-image: url(/common/template/sidebar/images/logo.jpg);"></div>
      <h3>
        <sec:authorize access="isAuthenticated()">
          <sec:authentication property="principal.fullname" />
        </sec:authorize>
      </h3>
    </div>
  </div>
  <ul class="list-unstyled components mb-5">
    <li id="act-building">
      <a href='<c:url value="/admin/building" /> '><span class="fa fa-home mr-3"></span> Quản lý tòa nhà</a>
    </li>
    <li id="act-customer">
      <a href='<c:url value="/admin/customer" /> '><span class="fa fa-users mr-3"></span> Quản lý khách hàng</a>
    </li>
    <sec:authorize access="hasAuthority('ADMIN')">
      <li id="act-user">
        <a href='<c:url value="/admin/user" /> '><span class="fa fa-male mr-3"></span> Quản lý người dùng</a>
      </li>
    </sec:authorize>
    <li id="act-account">
      <a href='<c:url value="/admin/account" /> '><span class="fa fa-cogs mr-3"></span> Quản lý tài khoản</a>
    </li>
    <li>
    <a href='<c:url value="/logout" /> '><span class="fa fa-sign-out mr-3"></span> Đăng xuất</a>
    </li>
  </ul>
</nav>