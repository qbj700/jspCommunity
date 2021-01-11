<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	int articleId = (int) request.getAttribute("articleId");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 등록</title>
</head>
<body>
	<h1>게시물 등록</h1>
	
	<div>
	<h2><%=articleId%>번 게시물이 등록 되었습니다.</h2>	
	</div>
</body>
</html>
