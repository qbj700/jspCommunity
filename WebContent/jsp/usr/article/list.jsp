<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Board"%>
<%
	Board board = (Board) request.getAttribute("board");
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=board.name%> 게시판 게시물 리스트</title>
<link href="/jspCommunity/resource/static/common.css" rel="stylesheet"></script>
</head>

<body>
	<h1><%=board.name%> 게시판 게시물 리스트</h1>
	
	<div>
		<a href="write?boardId=<%=request.getParameter("boardId")%>">게시물 작성</a>
	</div>
	
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
		<a href="detail?id=<%=article.id%>"><%=article.title%></a>
		<hr />
	</div>
	<%
		}
	%>
</body>
</html>
