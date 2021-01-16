<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=article.getId() %>번 게시물 수정페이지</title>
</head>
<body>
	<h1><%=article.getId() %>번 게시물 수정페이지</h1>

	<div>
		<form action="doModify" method="POST">
			<input type="hidden" name="id" value="<%=article.getId() %>">
			<hr>
			<input type="text" name="title" placeholder="제목을 입력해주세요." maxlength="50" value="<%=article.getTitle()%>">
			<hr>
			<textarea type="text" name="body" placeholder="내용을 입력해주세요." maxlength="5000" ><%=article.getBody()%></textarea>
			<hr>
			<input type="submit" value="전송">
			<button type="button" onclick="history.back()">뒤로가기</button>
			<hr>
		</form>	
	</div>
</body>
</html>
