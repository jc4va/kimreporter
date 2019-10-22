<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>ListAll</title>
</head>
<body>
<table class="table table-bordered">
<tr>
	<th style="width: 10px">adaptation_id</th>
	<th>adaptation content</th>
	<th>user id</th>
	<th>article title</th>
	<th>article content</th>
</tr>

<c:forEach items="${list}" var="AdaptationVO">
<tr>
	<td><a href="/adaptation/read?adaptation_id=${AdaptationVO.adaptation_id}"> ${AdaptationVO.article_title}</td>
	<td>${AdaptationVO.adaptation_id}</td>
	<td>${AdaptationVO.adaptation_content}</td>
	<td>${AdaptationVO.user_id}</td>
	<td>${AdaptationVO.article_content}</td>
</tr>
</c:forEach>

</table>
</body>
</html>
