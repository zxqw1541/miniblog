<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset='UTF-8'>
  <title>프로젝트-등록</title>
</head>
<body>
<h1>새 프로젝트</h1>
<form id='form1' action='add.do' method='post'>
<table border='1'>
<tr>
  <th>프로젝트명</th>
  <td><input type='text' name='title' size='50'></td>
</tr>
<tr>
  <th>시작일</th>
  <td><input type='date' name='startDate'></td>
</tr>
<tr>
  <th>종료일</th>
  <td><input type='date' name='endDate'></td>
</tr>
<tr>
  <th>회원</th>
  <td><input type='text' name='member' size='50' 
      placeholder='회원 이름을 입력하세요. 예)홍길동,임꺽정'></td>
</tr>
</table>

<p><button type='submit'>등록</button></p>

</form>

<jsp:include page="/Copyright.jsp"/>

</body>
</html>