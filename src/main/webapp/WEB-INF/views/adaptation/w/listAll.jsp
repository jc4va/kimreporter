<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>김기자 뉴스</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,,500,600,700"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
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
			<a class="navbar-brand" href="/">김기자 뉴스</a>
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
							<li class="nav-item cta"><a href="/adaptation/w/listAll"
								class="nav-link"><span>번안하기</span></a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->


	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h2 class="mb-4">실시간 뉴스 랭킹</h2>
					<p>다음 뉴스 중 요약이 제공된 뉴스입니다. 리스트는 한 시간마다 갱신됩니다.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 ftco-animate">
					<c:forEach items="${list}" var="AdaptationVO">
						<div id="accordion">
							<div class="row">
								<div class="col-md-12">
									<div class="card">
										<div class="card-header">
											<a class="card-link" data-toggle="collapse" href='#menuOne${AdaptationVO.adaptation_id}' 
												aria-expanded="true" aria-controls='menuOne${AdaptationVO.adaptation_id}' style="text-align:center;">
												${AdaptationVO.article_title} <span class="collapsed"><i
													class="ion-ios-arrow-up"></i></span><span class="expanded"><i
													class="ion-ios-arrow-down"></i></span>
											</a>
										</div>
										<div id='menuOne${AdaptationVO.adaptation_id}' class="collapse">
											<div class="card-body">
												<p>${AdaptationVO.article_content}</p>
												<span class="subheading"><a href="https://news.v.daum.net/v/${AdaptationVO.adaptation_id}">기사 전문</a></span>
											</div>
											</hr>
											<form
												action="/adaptation/w/modify?adaptation_id=${AdaptationVO.adaptation_id}"
												role="form" method="post">
												<input type="hidden" name="user_id" id="user_id"
													value='${sessionScope.login.user_id}' />
												<textarea class=form-control name="adaptation_content"
													rows=5 id="adaptation_content" style="resize: none;">${AdaptationVO.adaptation_content} </textarea>
												</br>
												<div class="form-group" style="text-align: center;">
													<input type="submit" value="제출"
														class="btn btn-primary py-1 px-3"
														style="border-radius: 10%;">
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>

	<footer class="ftco-footer ftco-bg-dark ftco-section">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md">
					<div class="ftco-footer-widget mb-4 bg-primary p-4">
						<h2 class="ftco-heading-2">김기자 뉴스</h2>
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
									class="text">서울시 중구 삼일대로 358 9층</span></li>
								<li><a href="#"><span class="icon icon-phone"></span><span
										class="text">010-2895-3546</span></a></li>
								<li><a href="#"><span class="icon icon-envelope"></span><span
										class="text">kimreporter@gmail.com</span></a></li>
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
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});
	});
</script>
