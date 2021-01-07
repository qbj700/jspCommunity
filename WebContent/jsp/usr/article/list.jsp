<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
	List<Map<String, Object>> articleListMap = (List<Map<String, Object>>) request.getAttribute("articleListMap");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	<%
		for (Map<String, Object> articleMap : articleListMap) {
	%>
	<div>
		번호 :
		<%=articleMap.get("id")%>
		<br /> 작성일 :
		<%=articleMap.get("regDate")%>
		<br /> 작성자 :
		<%=articleMap.get("extra__writer")%>
		<br /> 제목 :
		<%=articleMap.get("title")%>
		<br /> 내용 :
		<%=articleMap.get("body")%>
		<hr />
	</div>
	<%
		}
	%>
</body>
</html>
