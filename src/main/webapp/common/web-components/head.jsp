<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/web-components/taglib.jsp" %>

<%@ page import="com.javaweb.utils.SecurityUtils" %>


<script>
	const CONST_HOST_URL = "http://localhost:8080";
	const CONST_ROLE = `${SecurityUtils.getAuthorities()}`;
	const CONST_USER = JSON.parse(`${SecurityUtils.getPrincipalAsJson()}`);
	console.log(sessionStorage);
	if (!(!!sessionStorage.getItem('id')) || (!!sessionStorage.getItem('id') && sessionStorage.getItem('id') != CONST_USER.id)) {
		sessionStorage.setItem('id', CONST_USER.id);
		if (!!CONST_USER.avatar)
			sessionStorage.setItem('avatar', CONST_USER.avatar);
		if (!!CONST_USER.fullname)
			sessionStorage.setItem('fullname', CONST_USER.fullname);
	}
</script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Dashboard - Ace Admin</title>

<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />


<meta name="description" content="overview &amp; stats" />

<link rel="stylesheet" href="/common/template/sidebar/css/style.css">

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href='<c:url value="/common/template/css/bootstrap.min.css" /> ' />
<link rel="stylesheet" href='<c:url value="/common/template/css/bootstrap-theme.min.css" /> ' />
<!-- Optional theme -->
<link rel="stylesheet" href='<c:url value="/common/template/assets/font-awesome/4.2.0/css/font-awesome.min.css" />' />
<link rel="stylesheet" href='<c:url value="/common/template/assets/fonts/fonts.googleapis.com.css" />' />
<link rel="stylesheet" class="ace-main-stylesheet" id="main-ace-style"
	href='<c:url value="/common/template/assets/css/ace.min.css" /> ' />
<link rel="stylesheet" href='<c:url value="/common/custom/pagination.css" />' />

<link rel="stylesheet" href='<c:url value="/common/template/css/jquery-confirm.min.css" />' />