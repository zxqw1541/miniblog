<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset='UTF-8'>
  <title>${admin.name}의 Blog</title>
</head>
<body>
<h3>새 앨범만들기</h3>
<form action='add.do' method='post'
      enctype="multipart/form-data">
<table border='1'>
<tr>
  <th>제목</th>
  <td><input type='text' name='title' placeholder="제목을 넣으세요."></td>
</tr>
<tr>
  <th>장소</th>
  <td><input type='text' name='site' placeholder="장소를 넣으세요"></td>
</tr>
<tr>
  <th>내용</th>
  <td><textarea rows='10' name='content' cols='60'
       placeholder="내용을 입력하세요."></textarea></td>
</tr>
<tr>
  <th>첨부파일</th>
  <td><input type='file' name='file'><br>
  <input type='file' name='file2'><br>
  <input type='file' name='file3'><br>
  <input type='file' name='file4'><br>
  <input type='file' name='file5'></td>
</tr>
</table>

<p><button type='submit'>등록</button></p>

</form>

<jsp:include page="/Copyright.jsp"/>

</body>
</html>
