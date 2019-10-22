<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<html>
<head>
	<title>Update</title>
</head>
<body>
<form action="/adaptation/modify" role="form" method="post">
	<div class="form-group">
		<label for="adaptation_id"> Adaptation ID </label> <input type="text" 
		name="adaptation_id" id="adaptation_id" class="form-control" value="${adaptationVO.adaptation_id}"
		readonly="readonly">
	</div>
	<div class="form-group">
		<label for="article_title"> Article Title </label> <input type="text" 
		name="article_title" id="article_title" class="form-control" value="${adaptationVO.article_title}"
		readonly="readonly">
	</div>
	<div class="form-group">
		<label for="article_content"> Article Content </label> <input type="text" 
		name="article_content" id="article_content" class="form-control" value="${adaptationVO.article_content}"
		readonly="readonly">
	</div>
	<div class="form-group">
		<label for="adaptation_content"> Adaptation Content </label> <input type="text"
		name="adaptation_content" id="adaptation_content" class="form-control" value="${adaptationVO.adaptation_content}">
	</div>
</form>
<p><button type="submit" class="btn btn-primary"> 제출 </button></p>

</body>
</html>

<script>
$(document).ready(function() {
	var formObj = $("form[role='form']");
	console.log(formObj);

	$(".btn-primary").on("click", function() {
		formObj.submit();
	});
});
</script>