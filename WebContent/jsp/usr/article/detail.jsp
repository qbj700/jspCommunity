<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="${article.id}번 게시물 상세페이지"/>

<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle }</h1>

<div>
	<a
		href="modify?id=${article.id}&memberId=${article.memberId}"><button
			type="button">게시물 수정</button></a> <a
		onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }"
		href="doDelete?id=${article.id}&memberId=${article.memberId}"><button
			type="button">게시물 삭제</button></a>
</div>

<div>
	번호 :
	${article.id}
	<br /> 조회수 :
	${article.hitsCount}
	<br /> 작성일 :
	${article.regDate}
	<br /> 수정일 :
	${article.updateDate}
	<br /> 작성자 :
	${article.extra__writer}
	<br /> 제목 :
	${article.title}
	<br /> 내용 :
	${article.body}
</div>

<hr />

<div>
	<a href="list?boardId=${article.boardId}">리스트로 이동</a>
</div>
<%@ include file="../../part/foot.jspf"%>
