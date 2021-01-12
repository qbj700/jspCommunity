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
<title><%=article.id %>번 게시물 상세페이지</title>
</head>
<body>
	<h1><%=article.id %>번 게시물 상세페이지</h1>
	
	<div>
		<a href="modify?id=<%=article.id%>&memberId=<%=article.memberId%>"><button type="button" >게시물 수정</button></a>
		<a href="delete?id=<%=article.id%>&memberId=<%=article.memberId%>"><button type="button" >게시물 삭제</button></a>
	</div>
	
	<div>
		번호 :
		<%=article.id%>
		<br /> 조회수 :
		<%=article.hitsCount%>
		<br /> 작성일 :
		<%=article.regDate%>
		<br /> 수정일 :
		<%=article.updateDate%>
		<br /> 작성자 :
		<%=article.extra__writer%>
		<br /> 제목 :
		<%=article.title%>
		<br /> 내용 :
		<%=article.body%>
	</div>
	
	<hr />
	
	<div>
		<a href="list?boardId=<%=article.boardId%>">리스트로 이동</a>
	</div>
</body>
</html>
