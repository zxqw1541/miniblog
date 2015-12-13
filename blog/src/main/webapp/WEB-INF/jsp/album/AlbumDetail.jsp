<%-- 게시물 상세정보 및 변경 폼 --%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판-상세정보</title>
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

<div class='container'>
<c:if test="${not empty album}">
	<hr class='space'>
	<h1 class='white'>${album.title}</h1>
	<div class='rightButton'>
    <button class="md-trigger" onclick="history.back()">뒤로</button>
    <button class="md-trigger">수정</button>
    <button class="md-trigger">삭제</button>
  </div>
	
	
	<div class='span-24 maincontent'>
		<!-- MAIN CONTENT STARTS HERE -->
		<!-- MIDDLE CONTENT STARTS HERE -->
		<!-- one blog post -->
		<div class='blogPost'>
			<div class='blogPhoto'>
				<img src='../blog/pictures/${album.attachFile}'></a>
			</div>
			<div class='blogText'>
			  <p>${album.comment}</p>
			</div>
			<hr>
			<div class='blogText'>
				<p>${album.site}</p>
			</div>
			<hr>
			<div class='blogText'>
				<p>'writer'로 맴버이름 값 넘겨주세요 ${writer}</p>
			</div>
			<hr>
			<div class='blogText'>
        <p>${album.createdDate}</p>
      </div>
      <hr>
		</div>
	</div>
	</c:if>
  <hr class='space'>

	<c:if test="${empty album}">
		<p>해당 번호의 게시물을 찾을 수 없습니다.</p>
	</c:if>

	<jsp:include page="/Copyright.jsp" />
</div>
</body>
</html>
