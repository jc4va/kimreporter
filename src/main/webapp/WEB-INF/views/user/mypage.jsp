<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>친절한 김기자</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/png" 
	href="${pageContext.request.contextPath}/resources/images/favicon.png">
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/images/apple-touch-icon.png">
<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,,500,600,700"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/animate.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/aos.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/flaticon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/icomoon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="/">
		      <img alt="KimReporter" src="${pageContext.request.contextPath}/resources/images/logo.png"
	         width=115" height="30">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<c:choose>
						<c:when test='${sessionScope.login.user_id eq null}'>
							<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
							<li class="nav-item cta"><a href="/user/register"
								class="nav-link"><span>회원가입</span></a></li>
						</c:when>
						<c:when test='${sessionScope.login.user_id ne null}'>
							<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
							<li class="nav-item"><a class="nav-link" href="/user/myadaptation">내 번안</a></li>
							<li class="nav-item"><a class="nav-link" href="/user/mypage">마이페이지</a></li>
							<li class="nav-item cta"><a href="/adaptation/w/listAll"
								class="nav-link"><span>번안하기</span></a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<section class="ftco-section contact-section ftco-degree-bg">
		<div class="container">
			<div class="row d-flex contact-info">
				<div class="col-md-12 mb-4">
					<h1 class="mb-3 bread" style="text-align: center;">마이페이지</h1>
					<form role="form" method="post" autocomplete="off">
						<div class="form-group" style="text-align: center;">
							<p> 이름 </p>
							<input type="text" name="user_name" class="form-control"
								value="${userInfoVO.user_name}">
						</div>
						<div class="form-group" style="text-align: center;">
							<p> 이메일 </p>
							<input type="email" name="user_email" class="form-control"
								value="${userInfoVO.user_email}" readonly>
						</div>
						<div class="form-group" style="text-align: center;">
							<p> 아이디 </p>
							<input type="text" name="user_id" class="form-control"
								value="${userInfoVO.user_id}" readonly>
						</div>
						<div class="form-group" style="text-align: center;">
							<p> 비밀번호 </p>
							<input type="password" name="user_pwd" id="user_pwd" class="form-control">
						</div>
						<div class="form-group" style="text-align: center;">
							<p> 비밀번호 확인 </p>
							<input type="password" name="user_pwd_confirm" id="user_pwd_confirm"
							class="form-control" onChange="checkPassword();">
						</div>
						<div id="divCheckPasswordMatch"></div>
						<div class="form-group" style="text-align: center;">
							<input type="submit" value="확인" id="register" name="register"
								class="btn btn-primary py-3 px-5">
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<footer class="ftco-footer ftco-bg-dark ftco-section">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md">
					<div class="ftco-footer-widget mb-4 bg-primary p-4">
						<h2 class="ftco-heading-2">친절한 김기자</h2>
						<ul class="ftco-footer-social list-unstyled mb-0">
							<li class="ftco-animate"><a href="#"><span
									class="icon-twitter"></span></a></li>
							<li class="ftco-animate"><a href="#"><span
									class="icon-facebook"></span></a></li>
							<li class="ftco-animate"><a href="#"><span
									class="icon-instagram"></span></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">Office</h2>
						<div class="block-23 mb-3">
							<ul>
								<li><span class="icon icon-map-marker"></span><span
									class="text">서울시 중구 삼일대로 358 신한L타워, 9층 SK 이노베이션 Lab 8</span></li>
								<li><a href="#"><span class="icon icon-phone"></span><span
										class="text">010-3344-1365</span></a></li>
								<li><a href="#"><span class="icon icon-envelope"></span><span
										class="text">jhlican@naver.com</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">

					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>document.write(new Date().getFullYear());</script>
						All rights reserved | This template is made with <i
							class="icon-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>


	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/jquery-migrate-3.0.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.easing.1.3.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.waypoints.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.stellar.min.js" />"></script>
	<script src="<c:url value="/resources/js/owl.carousel.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/jquery.magnific-popup.min.js"/>"></script>
	<script src="<c:url value="/resources/js/aos.js"/>"></script>
	<script
		src="<c:url value="/resources/js/jquery.animateNumber.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.timepicker.min.js"/>"></script>
	<script src="<c:url value="/resources/js/scrollax.min.js" />"></script>
	<script
		src="<c:url value="/resources/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"/>"></script>
	<script src="<c:url value="/resources/js/google-map.js"/>"></script>
	<script src="<c:url value="/resources/js/main.js"/>"></script>


</body>
</html>

<script>
function checkPassword() {
    var password = $("#user_pwd").val();
    var confirmPassword = $("#user_pwd_confirm").val();

    if (password != confirmPassword){
        $("#divCheckPasswordMatch").html("비밀번호가 일치하지 않습니다.").css('color', 'red');
        $('#register').attr('disabled', true);
    }
    else {
        $("#divCheckPasswordMatch").html("비밀번호가 일치합니다.").css('color', 'green');
        $('#register').attr('disabled', false);
    }
}

$(document).ready(function () {
   $("#user_pwd, #user_pwd_confirm").keyup(checkPassword);
});

</script>