<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<html>
<head>
	<title>김기자 뉴스</title>
</head>
<body>

<nav class="navbar navbar-dark bg-primary">
  <a class="navbar-brand" href="/adaptation/w/listAll">김기자 뉴스</a>
</nav>

<button type="button" class="btn btn-primary" onclick="location.href='/user/register'">회원가입</button>
<button type="button" class="btn btn-primary" onclick="location.href='/user/login'">로그인</button>
</body>
</html>
