<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	String boardName = (String) request.getAttribute("boardName");
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=boardName%> 게시판 게시물 리스트</title>
<link href="<%=request.getContextPath()%>/resource/static/common.css" rel="stylesheet"></script>
</head>

<body>
	<h1><%=boardName%>
		게시판 게시물 리스트
		
		<%=request.getContextPath()%>
	</h1>
	<%
		for (Article article : articles) {
	%>
	<div class="list">
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
	<%
		}
	%>
</body>
</html>
