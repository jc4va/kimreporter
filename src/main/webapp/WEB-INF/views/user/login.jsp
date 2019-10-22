<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

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