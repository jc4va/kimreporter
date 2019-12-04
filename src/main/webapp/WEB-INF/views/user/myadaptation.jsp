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
	<section class="ftco-section">
		<div class="container">
			<h1 class="mb-3 bread" style="text-align: center;">내 번안</h1>
		</div>
	</section>


	<section class="ftco-section ftco-counter img" id="section-counter">
		<div class="container">
			<div class="row justify-content-center mb-5">
				<div
					class="col-md-7 text-center heading-section heading-section-white ftco-animate">
					<span class="subheading">김기자 뉴스와 함께 해 주셔서 감사합니다.</span>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-10">
					<div class="row">
						<div
							class="col-md-6 d-flex justify-content-center counter-wrap ftco-animate">
							<div class="block-18 text-center">
								<div class="text">
									<strong class="number" id="total_time" data-number="0">0</strong> <span>총
										봉사 시간 (분)</span>
								</div>
							</div>
						</div>
						<div
							class="col-md-6 d-flex justify-content-center counter-wrap ftco-animate">
							<div class="block-18 text-center">
								<div class="text">
									<strong class="number" id="total_count" data-number="${count}">0</strong> <span>번안한
										기사 수</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h2 class="mb-4">내가 번안한 뉴스</h2>
						<c:if test='${count == 0}'>
							<p>아직 번안한 뉴스가 없어요.</p>
						</c:if>
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
												<span class="subheading"><a target="_blank" href="https://news.v.daum.net/v/${AdaptationVO.adaptation_id}">기사 전문</a></span>
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
	
	<script>
		document.getElementById("total_time").setAttribute("data-number", ${count} * 10);
	</script>


</body>
</html>