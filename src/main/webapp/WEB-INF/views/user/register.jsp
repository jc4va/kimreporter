<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>김기자 뉴스</title>
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
  <a class="navbar-brand" href="/adaptation/w/listAll">김기자 뉴스</a>
</nav>

<form role="form" method="post" autocomplete="off">
 <p>
  <label for="user_id">아이디</label>
  <input class="form-control" type="text" id="user_id" name="user_id" />
 </p>
  <p>
  <label for="user_name">이름 </label>
  <input class="form-control" type="text" id="user_name" name="user_name" />
 </p>
  <p>
  <label for="user_pwd">비밀번호   </label>
  <input class="form-control" type="password" id="user_pwd" name="user_pwd" />
 </p>
 <p>
  <label for="user_email">이메일 </label>
  <input class="form-control" type="text" id="user_email" name="user_email" />
 </p>
 <p><button type="submit">회원가입 </button></p>
</form>

</body>
</html>