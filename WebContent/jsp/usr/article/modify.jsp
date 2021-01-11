<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	int id = (int)request.getAttribute("id");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 수정</title>
</head>
<body>
	<h1>게시물 수정</h1>

	<div>
		<form action="/jspCommunity/usr/article/modifyResult" method="POST" target="_blank">
			<input type="hidden" name="id" value="<%=id %>">
			<hr>
			<input type="text" name="title" placeholder="제목을 입력해주세요." maxlength="100">
			<hr>
			<textarea type="text" name="body" placeholder="내용을 입력해주세요." maxlength="1000"></textarea>
			<hr>
			<input type="submit" value="전송">
			<hr>
		</form>	
	</div>
</body>
</html>
