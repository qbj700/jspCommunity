<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${article.id}번 게시물 수정페이지" />

<%@ include file="../../part/head.jspf"%>

<div class="title-bar con-min-width">
	<h1 class="con padding-0-10">
		<span>${pageTitle }</span>
	</h1>
</div>


<div class="con-min-width">
	<div class="con padding-0-10">
		<div>
			<script>
				let DoModifyForm__submited = false;
				let DoModifyForm__checkedLoginId = "";

				// 폼 발송전 체크
				function DoModifyForm__submit(form) {
					if (DoModifyForm__submited) {
						alert('처리중입니다.');
						return;
					}

					form.title.value = form.title.value.trim();

					if (form.title.value.length == 0) {
						alert('제목을 입력해주세요.');
						form.title.focus();

						return;
					}

					const editor = $(form).find('.toast-ui-editor').data(
							'data-toast-editor');
					const body = editor.getMarkdown().trim();

					if (body.length == 0) {
						alert('내용을 입력해주세요.');
						editor.focus();

						return;
					}

					form.body.value = body;

					form.submit();
					DoModifyForm__submited = true;
				}
			</script>
			<form action="doModify" method="POST"
				onsubmit="DoModifyForm__submit(this); return false;">
				<input type="hidden" name="id" value="${article.id}"> <input
					type="hidden" name="body" />
				<hr>
				<input type="text" name="title" placeholder="제목을 입력해주세요."
					maxlength="50" value="${article.title}">
				<hr>
				<script type="text/x-template">${article.body}</script>
				<div class="toast-ui-editor"></div>
				<hr>

				<div class="article-btn-box">
					<div class="btn-wrap">
						<input type="submit" class="btn btn-primary" value="수정"> <a
							type="button" class="btn btn-info" onclick="history.back()">뒤로가기</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="../../part/foot.jspf"%>