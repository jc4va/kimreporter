<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>김기자 뉴스</title>
</head>
<body>

<nav class="navbar navbar-dark bg-primary">
  <a class="navbar-brand" href="/adaptation/w/listAll">김기자 뉴스</a>
</nav>

<form action="/user/loginPost" method="post">
	<div class="form-group has-feedback">
		<input type="text" name="user_id" class="form-control" placeholder="User ID" />
	</div>
	<div class="form-group has-feedback">
		<input type="password" name="user_pwd" class="form-control" placeholder="Password" />
	</div>
	<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
</form>

</body>
</html>