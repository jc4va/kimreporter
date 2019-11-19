<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>김기자 뉴스</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,,500,600,700" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/animate.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/aos.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">

    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/flaticon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icomoon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
  </head>
  <body>
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/">김기자 뉴스</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	        <c:choose>
	        	<c:when test='${sessionScope.login.user_id eq null}'>
	        		<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
	          		<li class="nav-item cta"><a href="/user/register" class="nav-link"><span>회원가입</span></a></li>
	          	</c:when>
	          	<c:when test='${sessionScope.login.user_id ne null}'>
	        		<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
	          		<li class="nav-item cta"><a href="/adaptation/w/listAll" class="nav-link"><span>번안하기</span></a></li>
				</c:when>
	        </c:choose>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <div class="hero-wrap js-fullheight">
      <div class="overlay"></div>
      <div class="container-fluid px-0">
      	<div class="row d-md-flex no-gutters slider-text align-items-center js-fullheight justify-content-end">
	      	<img class="one-third js-fullheight align-self-end order-md-last img-fluid" src="${pageContext.request.contextPath}/resources/images/undraw_pair_programming_njlp.svg" alt="">
	        <div class="one-forth d-flex align-items-center ftco-animate js-fullheight">
	        	<div class="text mt-5">
	        		<span class="subheading">모두를 위한 뉴스 번안</span>
	            <h1 class="mb-3"><span>협력하고,</span> <span>배려하는,</span> <span>김기자 뉴스</span></h1>
	            <p>김기자 뉴스는 발달장애, 경계성 지능장애를 가지고 있는 사람들을 위해 보다 쉬운 뉴스를 제공하는 사이트입니다.</p>
	            <p><a href="adaptation/w/listAll" class="btn btn-primary px-4 py-3">번안하기</a></p>
	          </div>
	        </div>
	    	</div>
      </div>
    </div>
  
  
    <section class="ftco-section services-section bg-light">
      <div class="container">
      	<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">김기자 뉴스와 함께 해 주세요.</h2>
            <p>다양한 이유로 뉴스 이해가 어려웠던 정보 소외계층에게 포털사이트 뉴스를 더 쉽게 요약하고, 번안해 읽어주는 서비스</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-center">
            	<div class="icon d-flex align-items-center justify-content-center order-md-last">
            		<span class="flaticon-cloud"></span>
            	</div>
              <div class="media-body pl-4 pl-md-0 pr-md-4 text-md-right">
                <h3 class="heading">1차 번안</h3>
                <p class="mb-0">단어 대치를 통한 1차 번안을 통해 어려운 법률·정치·경제 등 전문 용어를 번안하는 데 통일성을 부여하고, 2차 번안을 더욱 쉽게 만듭니다.</p>
              </div>
            </div>      
          </div>
          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-center">
            	<div class="icon d-flex align-items-center justify-content-center">
            		<span class="flaticon-server"></span>
            	</div>
              <div class="media-body pl-4">
                <h3 class="heading">2차 번안</h3>
                <p class="mb-0">전문 자원봉사자의 더 쉽고 섬세한 최종 번안이 AI 스피커를 통해 발화되고, 인기 있는 뉴스와 같이 사용자의 의도에 맞는 뉴스를 읽어줍니다.</p>
              </div>
            </div>    
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
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Office</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">서울시 중구 삼일대로 358 9층</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">010-2895-3546</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">kimreporter@gmail.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>

	
  <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
  <script src="<c:url value="/resources/js/jquery-migrate-3.0.1.min.js"/>"></script>
  <script src="<c:url value="/resources/js/popper.min.js" />"></script>
  <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.easing.1.3.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.waypoints.min.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.stellar.min.js" />"></script>
  <script src="<c:url value="/resources/js/owl.carousel.min.js"/>"></script>
  <script src="<c:url value="/resources/js/jquery.magnific-popup.min.js"/>"></script>
  <script src="<c:url value="/resources/js/aos.js"/>"></script>
  <script src="<c:url value="/resources/js/jquery.animateNumber.min.js"/>"></script>
  <script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
  <script src="<c:url value="/resources/js/jquery.timepicker.min.js"/>"></script>
  <script src="<c:url value="/resources/js/scrollax.min.js" />"></script>
  <script src="<c:url value="/resources/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"/>"></script>
  <script src="<c:url value="/resources/js/google-map.js"/>"></script>
  <script src="<c:url value="/resources/js/main.js"/>"></script>
    
  </body>
</html>