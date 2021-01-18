<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="${board.name} 게시판 게시물 등록"/>

<%@ include file="../../part/head.jspf"%>

<h1> ${pageTitle }]</h1>

<div>
	<form action="doWrite" method="POST">
		<input type="hidden" name="boardId" value="${board.id}">
		<input type="hidden" name="memberId" value="1">
		<hr>
		<input type="text" name="title" placeholder="제목을 입력해주세요."
			maxlength="50">
		<hr>
		<textarea type="text" name="body" placeholder="내용을 입력해주세요."
			maxlength="5000"></textarea>
		<hr>
		<input type="submit" value="전송">
		<button type="button" onclick="history.back()">뒤로가기</button>
		<hr>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>