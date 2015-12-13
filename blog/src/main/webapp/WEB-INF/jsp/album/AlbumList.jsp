<%-- EL을 이용하여 게시물 데이터 출력하기 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판-목록</title>
<!-- -->
<link rel="stylesheet" type="text/css" href="../css/default.css" />
<link rel="stylesheet" type="text/css" href="../css/component.css" />

<!--  -->
<link rel="stylesheet" href="../css/screen.css" type="text/css"
	media="screen, projection">
<link rel="stylesheet" href="../css/stylesheet.css" type="text/css"
	media="screen, projection">
<script src="../js/modernizr.custom.js"></script>
<!-- jQuery lib -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<!-- nivo slider -->
<script type="text/javascript" src="../js/jquery.nivo.slider.js"></script>
<link rel="stylesheet" href="../css/nivo.css" type="text/css"
	media="screen, projection">
<!-- jQuery carousel -->
<script type="text/javascript" src="../js/easypaginate.js"></script>
<!-- custom tooltip -->
<script type="text/javascript" src="../js/tooltip.js"></script>
</head>
<body>
	<jsp:include page="/Header.jsp" />

	<h1>게시판</h1>
	<div class="container">
		<div class='span-24 slider'>
			<div class='bevel'></div>
			<div class="slider-wrapper theme-nivo">
				<div id="slider" class="nivoSlider">
					<img src="../blog/pictures/2.jpg" alt="2">
					<img src="../blog/pictures/3.jpg" alt="3">
				  <img src="../blog/pictures/4.jpg" alt="4">
					<img src="../blog/pictures/5.jpg" alt="5">
          <img src="../blog/pictures/6.jpg" alt="6">
				</div>
			</div>
			<script type="text/javascript">
        jQuery(window).load(function(){
            jQuery("#slider").nivoSlider({
                effect:"random",
                slices:15,
                boxCols:8,
                boxRows:4,
                animSpeed:500,
                pauseTime:3000,
                startSlide:0,
                directionNav:false,
                directionNavHide:false,
                controlNav:true,
                controlNavThumbs:false,
                controlNavThumbsFromRel:true,
                keyboardNav:true,
                pauseOnHover:true,
                manualAdvance:false,
                borderRadius: 5                
            });
        });
        </script>
			<div class='arrowUp'></div>
		</div>
		<hr class='space'>
		<a href='add.do'>새 글</a>
		<hr class='space'>
		<c:forEach var="album" items="${albums}" varStatus="status">
		  <c:if test='${status.count % 3 != 0}'>
				<div class="span-8 smallSquare">
					<a href='detail.do?no=${album.no}'><img src='../blog/pictures/${album.attachFile}' alt='3'></a>
					<div class='arrowUp'></div>
					<div class='box'>
						<h6>${album.title}</h6>
						<p>${status.count}</p>
					</div>
				</div>
			</c:if>
			<c:if test='${status.count % 3 == 0}'>
				<div class="span-8 last smallSquare">
					<a href='detail.do?no=${album.no}'><img src='../blog/pictures/${album.attachFile}' alt='4'></a>
					<div class='arrowUp'></div>
					<div class='box'>
						<h6>${album.title}</h6>
            <p>${status.count}</p>
					</div>
				</div>
				<hr class='space'>
			</c:if>
		</c:forEach>
		
		<hr class='space'>

	<jsp:include page="/Copyright.jsp" />

</body>
</html>





