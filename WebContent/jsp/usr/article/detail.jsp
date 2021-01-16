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
<title><%=article.getId() %>번 게시물 상세페이지</title>
</head>
<body>
	<h1><%=article.getId() %>번 게시물 상세페이지</h1>
	
	<div>
		<a href="modify?id=<%=article.getId()%>&memberId=<%=article.getMemberId()%>"><button type="button" >게시물 수정</button></a>
		<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }"href="doDelete?id=<%=article.getId()%>&memberId=<%=article.getMemberId()%>"><button type="button" >게시물 삭제</button></a>
	</div>
	
	<div>
		번호 :
		<%=article.getId()%>
		<br /> 조회수 :
		<%=article.getHitsCount()%>
		<br /> 작성일 :
		<%=article.getRegDate()%>
		<br /> 수정일 :
		<%=article.getUpdateDate()%>
		<br /> 작성자 :
		<%=article.getExtra__writer()%>
		<br /> 제목 :
		<%=article.getTitle()%>
		<br /> 내용 :
		<%=article.getBody()%>
	</div>
	
	<hr />
	
	<div>
		<a href="list?boardId=<%=article.getBoardId()%>">리스트로 이동</a>
	</div>
</body>
</html>
