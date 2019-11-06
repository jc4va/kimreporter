<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>김기자 뉴스</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<a href="/" class="logo">intensify</a>
				<nav class="right">
				<c:choose>
					<c:when test='${sessionScope.login.user_id eq null}'>
						<a href="/user/login" class="button alt">로그인</a>
						<a href="/user/register" class="button alt">회원가입</a>
					</c:when>
					<c:when test='${sessionScope.login.user_id ne null}'>
						<a href="/user/logout" class="button alt">로그아웃</a>
						<a href="/adaptation/w/listAll" class="button alt">번안하기</a>
					</c:when>
				</c:choose>
				</nav>
			</header>

		<!-- Banner -->
			<section id="banner">
				<div class="content">
					<h1>김기자 뉴스</h1>
					<p>모두를 위한<br />읽기 쉬운 뉴스</p>
				</div>
			</section>

		<!-- One -->
			<section id="one" class="wrapper">
				<div class="inner flex flex-3">
					<div class="flex-item left">
						<div>
							<h3>Magna ultricies</h3>
							<p>Morbi in sem quis dui plalorem ipsum<br /> euismod in, pharetra sed ultricies.</p>
						</div>
						<div>
							<h3>Ipsum adipiscing lorem</h3>
							<p>Tristique yonve cursus jam nulla quam<br /> loreipsu gravida adipiscing lorem</p>
						</div>
					</div>
					<div class="flex-item image fit round">
						<img src="/resources/images/pic01.jpg" alt="" />
					</div>
					<div class="flex-item right">
						<div>
							<h3>Tempus nullam</h3>
							<p>Sed adipiscing ornare risus. Morbi estes<br /> blandit sit et amet, sagittis magna.</p>
						</div>
						<div>
							<h3>Suscipit nibh dolore</h3>
							<p>Pellentesque egestas sem. Suspendisse<br /> modo ullamcorper feugiat lorem.</p>
						</div>
					</div>
				</div>
			</section>


		<!-- Footer -->
			<footer id="footer">
				<div class="inner">
					<h2>Get In Touch</h2>
					<ul class="actions">
						<li><span class="icon fa-phone"></span> <a href="#">(000) 000-0000</a></li>
						<li><span class="icon fa-envelope"></span> <a href="#">information@untitled.tld</a></li>
						<li><span class="icon fa-map-marker"></span> 123 Somewhere Road, Nashville, TN 00000</li>
					</ul>
				</div>
				<div class="copyright">
					&copy; Untitled. Design <a href="https://templated.co">TEMPLATED</a>. Images <a href="https://unsplash.com">Unsplash</a>.
				</div>
			</footer>
	</body>
	
		<!-- Scripts -->
			<script src="<c:url value="/resources/js/jquery.min.js" />">
			<script src="<c:url value="/resources/js/jquery.scrolly.min.js" />">
			<script src="<c:url value="/resources/js/main.js" />">
			<script src="<c:url value="/resources/js/skel.min.js" />">
			<script src="<c:url value="/resources/js/util.min.js" />">
</html>