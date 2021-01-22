<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle }</h1>

<div>
	<script>
		let DoJoinForm__submited = false;
		let DoJoinForm__checkedLoginId = "";
		
		// 로그인 아이디 중복체크
		function DoJoinForm__checkLoginIdDup(el) {
			const from = $(el).closest('form').get(0);
			const loginId = from.loginId.value.trim();
			
			if (loginId.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();

				return;
			}
			
			$.get(
				"getLoginIdDup",
				{
					loginId
				},
				function(data) {
					if ( data.msg ) {
						alert(data.msg);
					}
					if ( data.resultCode.substr(0, 2) == "S-" ) {
						DoJoinForm__checkedLoginId = data.loginId;
					}
				},
				"json"
			);
		}
		
		function DoJoinForm__submit(form) {
			if (DoJoinForm__submited) {
				alert('처리중입니다.');
				return;
			}

			form.loginId.value = form.loginId.value.trim();

			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();

				return;
			}

			if ( form.loginId.value != DoJoinForm__checkedLoginId ) {
				alert('로그인 아이디 중복검사를 해주세요.');
				form.btnLoginIdDupCheck.focus();
				return false;
			}

			form.loginPw.value = form.loginPw.value.trim();

			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요.');
				form.loginPw.focus();

				return;
			}

			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

			if (form.loginPwConfirm.value.length == 0) {
				alert('비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();

				return;
			}

			if (form.loginPw.value != form.loginPwConfirm.value) {
				alert('비밀번호가 일치하지 않습니다');
				form.loginPwConfirm.focus();

				return;
			}

			form.name.value = form.name.value.trim();

			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.name.focus();

				return;
			}

			form.nickname.value = form.nickname.value.trim();

			if (form.nickname.value.length == 0) {
				alert('별명을 입력해주세요.');
				form.nickname.focus();

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				alert('이메일을 입력해주세요.');
				form.email.focus();

				return;
			}

			form.cellphoneNo.value = form.cellphoneNo.value.trim();

			if (form.cellphoneNo.value.length == 0) {
				alert('전화번호를 입력해주세요.');
				form.cellphoneNo.focus();

				return;
			}

			form.submit();
			DoJoinForm__submited = true;
		}
	</script>
	<form action="doJoin" method="POST"
		onsubmit="DoJoinForm__submit(this); return false;">
		<hr>
		<div>로그인 아이디</div>
		<div>
			<input type="text" name="loginId" placeholder="아이디를 입력해주세요."
				maxlength="50">
				
			<button onclick="DoJoinForm__checkLoginIdDup(this);"
					name="btnLoginIdDupCheck" type="button">중복체크</button>
		</div>

		<hr>
		<div>로그인 비밀번호</div>
		<div>
			<input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요."
				maxlength="50">
		</div>

		<hr>
		<div>비밀번호 확인</div>
		<div>
			<input type="password" name="loginPwConfirm"
				placeholder="비밀번호를 입력해주세요." maxlength="50">
		</div>

		<hr>
		<div>이름</div>
		<div>
			<input type="text" name="name" placeholder="이름을 입력해주세요."
				maxlength="50">
		</div>

		<hr>
		<div>별명</div>
		<div>
			<input type="text" name="nickname" placeholder="별명을 입력해주세요."
				maxlength="50">
		</div>

		<hr>
		<div>이메일</div>
		<div>
			<input type="email" name="email" placeholder="이메일을 입력해주세요."
				maxlength="100">
		</div>

		<hr>
		<div>전화번호</div>
		<div>
			<input type="number" name="cellphoneNo" placeholder="전화번호를 입력해주세요."
				maxlength="50">
		</div>

		<hr>
		<div>가입</div>
		<div>
			<input type="submit" value="가입">
			<button type="button" onclick="history.back()">뒤로가기</button>
		</div>
		<hr>
	</form>
</div>

<%@ include file="../../part/foot.jspf"%>