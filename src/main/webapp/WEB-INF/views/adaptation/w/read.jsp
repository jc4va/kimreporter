<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<html>
<head>
	<title>Read</title>
</head>
<body>
<form role="form" method="post">
	<input type="hidden" name="adaptation_id" value="${adaptationVO.adaptation_id}">
</form>
<div class="box-body">
	<div class="form-group">
		<label for="Adaptation_Content"> Adaptation Content </label> <input type="text" 
		name="Adaptation_Content" class="form-control" value="${adaptationVO.adaptation_content}">
	</div>
	<div class="form-group">
		<label for="Article_Title"> Article Title </label> <input type="text" 
		name="Article_Title" class="form-control" value="${adaptationVO.article_title}"
		readonly="readonly">
	</div>
	<div class="form-group">
		<label for="Article_Content"> Article Content </label> <input type="text" 
		name="Article_Content" class="form-control" value="${adaptationVO.article_content}"
		readonly="readonly">
	</div>
	<p><button type="submit" class="btn btn-warning"> 수정 </button></p>
</div>
</body>
</html>

<script>
$(document).ready(function() {
	var formObj = $("form[role='form']");
	console.log(formObj);

	$(".btn-warning").on("click", function() {
		formObj.attr("action", "/adaptation/w/modify");
		formObj.attr("method", "get");
		formObj.submit();
	});
});
</script>
