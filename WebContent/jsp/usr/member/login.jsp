<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="로그인" />

<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle }</h1>

<div>
	<script>
		let DoLoginForm__submited = false;
		function DoLoginForm__submit(form) {
			if (DoLoginForm__submited) {
				alert('처리중입니다.');
				return;
			}

			form.loginId.value = form.loginId.value.trim();

			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();

				return;
			}

			form.loginPw.value = form.loginPw.value.trim();

			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요.');
				form.loginPw.focus();

				return;
			}

			form.submit();
			DoLoginForm__submited = true;
		}
	</script>
	<form action="doLogin" method="POST"
		onsubmit="DoLoginForm__submit(this); return false;">
		<hr>
		<div>로그인 아이디</div>
		<div>
			<input type="text" name="loginId" placeholder="아이디를 입력해주세요."
				maxlength="50">
		</div>

		<hr>
		<div>로그인 비밀번호</div>
		<div>
			<input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요."
				maxlength="50">
		</div>

		<hr>
		<div>로그인</div>
		<div>
			<input type="submit" value="로그인">
			<button type="button" onclick="history.back()">뒤로가기</button>
		</div>
		<hr>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>