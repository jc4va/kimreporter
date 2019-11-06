<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<html>

<head>
	<title>김기자 뉴스</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">

</head>

<body>

<!-- Header -->
	<header id="header">
		<a href="/" class="logo">intensify</a>
		<nav class="right">
			<a href="/user/logout" class="button alt">로그아웃</a>
			<a href="/adaptation/w/listAll" class="button alt">번안하기</a>
			<a href="/adaptation/w/listAll" class="button alt">마이페이지</a>
		</nav>
	</header>

<c:forEach items="${list}" var="AdaptationVO">
	<div class="accordion" id="adaptationList">
		<div class="card">
			<div class="card-header" id="heading">
				<h2 class="mb-0">
					<button class="btn btn-link" type="button" data-toggle="collapse" data-target='#${AdaptationVO.adaptation_id}' aria-expanded="true" aria-controls="collapseOne">
					${AdaptationVO.article_title}
					</button>
				</h2>
			</div>
			
			<div id='${AdaptationVO.adaptation_id}' class="collapse" aria-labelledby="heading" data-parent="#adaptationList">
			    <div class="card-body">
			      	${AdaptationVO.article_content}
			    </div>
			    <hr/>
			    <div class="card-body">
			    <form action="/adaptation/w/modify?adaptation_id=${AdaptationVO.adaptation_id}" role="form" method="post">
			    	<input type="hidden" name="user_id" id="user_id" value='${sessionScope.login.user_id}' />
			    	<textarea class=form-control name="adaptation_content" rows=5
			    	id="adaptation_content">${AdaptationVO.adaptation_content}</textarea>
			    	<button class="btn btn-primary" type="submit" > 제출 </button>
			    </form>
			    </div>
			</div>
		</div>
	</div>
</c:forEach>
</body>
</html>

<script src="<c:url value="/resources/js/jquery.min.js" />">
<script src="<c:url value="/resources/js/jquery.scrolly.min.js" />">
<script src="<c:url value="/resources/js/main.js" />">
<script src="<c:url value="/resources/js/skel.min.js" />">
<script src="<c:url value="/resources/js/util.min.js" />">

<script>
$(document).ready(function() {
	var formObj = $("form[role='form']");
	console.log(formObj);

	$(".btn-primary").on("click", function() {
		formObj.submit();
	});
});
</script>
