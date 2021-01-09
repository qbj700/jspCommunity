<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 상세</title>
</head>
<body>
	<h1>게시물 상세</h1>
	
	<div>
		번호 :
		<%=article.id%>
		<br /> 작성일 :
		<%=article.regDate%>
		<br /> 작성자 :
		<%=article.extra__writer%>
		<br /> 제목 :
		<%=article.title%>
		<br /> 내용 :
		<%=article.body%>
		<hr />
	</div>
</body>
</html>