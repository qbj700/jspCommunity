<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="${article.id}번 게시물 수정페이지"/>

<%@ include file="../../part/head.jspf"%>

<h1> ${pageTitle }</h1>

<div>
	<form action="doModify" method="POST">
		<input type="hidden" name="id" value="${article.id}">
		<hr>
		<input type="text" name="title" placeholder="제목을 입력해주세요."
			maxlength="50" value="${article.title}">
		<hr>
		<textarea type="text" name="body" placeholder="내용을 입력해주세요."
			maxlength="5000">${article.body}</textarea>
		<hr>
		<input type="submit" value="전송">
		<button type="button" onclick="history.back()">뒤로가기</button>
		<hr>
	</form>
</div>

<%@ include file="../../part/foot.jspf"%>
