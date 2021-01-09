<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	String message = (String) request.getAttribute("message");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 삭제</title>
</head>
<body>
	<h1>게시물 삭제</h1>

	<div>
		<h2><%=message %></h2>
	</div>
</body>
</html>
