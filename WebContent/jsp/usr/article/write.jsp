<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="${board.name} 게시판 게시물 등록"/>

<%@ include file="../../part/head.jspf"%>

<h1> ${pageTitle }</h1>

<div>
	<script>
	let DoWriteForm__submited = false;
	let DoWriteForm__checkedLoginId = "";
	
	// 폼 발송전 체크
	function DoWriteForm__submit(form) {
		if ( DoWriteForm__submited ) {
			alert('처리중입니다.');
			return;
		}
	
		form.title.value = form.title.value.trim();
	
		if ( form.title.value.length == 0 ) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			
			return;
		}
		
		const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
		const body = editor.getMarkdown().trim();
		
		if ( body.length == 0 ) {
			alert('내용을 입력해주세요.');
			editor.focus();
			
			return;
		}
		
		form.body.value = body;
		
		form.submit();
		DoWriteForm__submited = true;
	}
	</script>
	<form action="doWrite" method="POST" onsubmit="DoWriteForm__submit(this); return false;">
		<input type="hidden" name="boardId" value="${board.id}" />
		<input type="hidden" name="body" />
		
		<hr>
		<input type="text" name="title" placeholder="제목을 입력해주세요."
			maxlength="50">
		<hr>
			<script type="text/x-template"></script>
			<div class="toast-ui-editor"></div>
		<hr>
		<input type="submit" value="작성">
		<button type="button" onclick="history.back()">뒤로가기</button>
		<hr>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>