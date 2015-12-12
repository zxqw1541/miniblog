<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
프로젝트 관리 시스템 -
  <c:if test="${not empty loginUser}">
  ${loginUser.name}(${loginUser.cid})
  <a href="${pageContext.request.contextPath}/auth/logout.do">로그아웃</a>
  </c:if>
  <c:if test="${empty loginUser}">
    <a href="${pageContext.request.contextPath}/auth/login.do">로그인</a>
      <button class="md-trigger" data-modal="modal-1">로그인</button>
      <button class="md-trigger" data-modal="modal-10">회원가입</button>
    <a href="${pageContext.request.contextPath}/member/MemberReg.jsp">회원가입</a>
  </c:if>
</div>
<!-- /container -->

<div class="md-modal md-effect-1" id="modal-1">
  <div class="md-content">
    <h3>로그인</h3>
    <div>
      
      <form action='../auth/login.do' method="post">
      <ul>
        <li><strong>이메일</strong> <input type='text' name='email' value="${cookie.email.value}"></li>
        <li><strong>비밀번호</strong> <input type='password' name='password'></li>
      </ul>
      <button class="md-close">로그인</button>
      <input type="checkbox" name="saveEmail" ${(empty cookie.email)?"":"checked"}> 이메일 저장
      </form>
    </div>
  </div>
</div>

<div class="md-modal md-effect-10" id="modal-10">
  <div class="md-content">
    <h3>Modal Dialog</h3>
    <div>
      <p>This is a modal window. You can do the following things with
        it:</p>
      <ul>
        <li><strong>Read:</strong> modal windows will probably tell you
          something important so don't forget to read what they say.</li>
        <li><strong>Look:</strong> a modal window enjoys a certain kind
          of attention; just look at it and appreciate its presence.</li>
        <li><strong>Close:</strong> click on the button below to close
          the modal.</li>
      </ul>
      <button class="md-close">Close me!</button>
    </div>
  </div>
</div>


<div class="md-overlay"></div>
<!-- the overlay element -->

<!-- classie.js by @desandro: https://github.com/desandro/classie -->
<script src="../js/classie.js"></script>
<script src="../js/modalEffects.js"></script>

<!-- for the blur effect -->
<!-- by @derSchepp https://github.com/Schepp/CSS-Filters-Polyfill -->
<script>
      // this is important for IEs
      var polyfilter_scriptpath = '/js/';
    </script>
<script src="../js/cssParser.js"></script>
<script src="../js/css-filters-polyfill.js"></script>
