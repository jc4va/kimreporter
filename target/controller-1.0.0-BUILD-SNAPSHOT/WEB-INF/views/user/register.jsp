<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form role="form" method="post" autocomplete="off">
 <p>
  <label for="user_id">아이디</label>
  <input type="text" id="user_id" name="user_id" />
 </p>
  <p>
  <label for="user_name">이름 </label>
  <input type="text" id="user_name" name="user_name" />
 </p>
  <p>
  <label for="user_pwd">비밀번호   </label>
  <input type="password" id="user_pwd" name="user_pwd" />
 </p>
 <p>
  <label for="user_email">이메일 </label>
  <input type="text" id="user_email" name="user_email" />
 </p>
 <p><button type="submit">회원가입 </button></p>
</form>

</body>
</html>