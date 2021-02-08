<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원정보 수정" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<%@ include file="../../part/head.jspf"%>

<div class="title-bar con-min-width">
	<h1 class="con">
		<span>${pageTitle }</span>
	</h1>
</div>


<div class="con-min-width">
	<div class="con">
		<div>
			<style>
table {
	border: 3px solid skyblue;
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
</style>
			<script>
				function checkValue() {
					var form = document.userInfo;

					if (form.loginPw.value.trim().length > 0) {
						if (!form.loginPw.value.trim()) {
							alert("비밀번호를 입력하세요.");
							return false;
						}

						// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
						if (form.loginPw.value.trim() != form.loginPwConfirm.value
								.trim()) {
							alert("비밀번호를 동일하게 입력하세요.");
							return false;
						}
					}

					if (!form.name.value.trim()) {
						alert("이름을 입력하세요.");
						return false;
					}

					if (!form.email1.value.trim()) {
						alert("메일 주소를 입력하세요.");
						return false;
					}

					if (!form.cellphoneNo.value.trim()) {
						alert("전화번호를 입력하세요.");
						return false;
					}

					if (isNaN(form.cellphoneNo.value.trim())) {
						alert("전화번호는 - 제외한 숫자만 입력해주세요.");
						return false;
					}

					if (form.loginPw.value.length > 0) {
						form.loginPwReal.value = sha256(form.loginPw.value);
						form.loginPw.value = "";
						form.loginPwConfirm.value = "";
					}
				}
			</script>
			<form autocomplete="off" method="post" action="doModify"
				name="userInfo" onsubmit="return checkValue()">
				<input type="hidden" name="loginPwReal" />
				<table>
					<tr>
						<td id="title">아이디</td>
						<td>${loginedMember.loginId}</td>
					</tr>

					<tr>
						<td id="title">비밀번호</td>
						<td><input type="password" name="loginPw" maxlength="50">
						</td>
					</tr>

					<tr>
						<td id="title">비밀번호 확인</td>
						<td><input type="password" name="loginPwConfirm"
							maxlength="50"></td>
					</tr>

					<tr>
						<td id="title">이름</td>
						<td><input type="text" name="name" maxlength="50"
							value="${loginedMember.name}"></td>
					</tr>
					<tr>
						<td id="title">별명</td>
						<td><input type="text" name="nickname" maxlength="50"
							value="${loginedMember.nickname}"></td>
					</tr>

					<tr>
						<td id="title">이메일</td>
						<td><input type="text" name="email1" maxlength="50"
							value="${email1}">&nbsp;@ <select name="email2">
								<option hidden>${email2}</option>
								<option>naver.com</option>
								<option>daum.net</option>
								<option>gmail.com</option>
								<option>nate.com</option>
						</select></td>
					</tr>
					<tr>
						<td id="title">휴대전화</td>
						<td><input type="tel" name="cellphoneNo"
							value="${loginedMember.cellphoneNo}" /></td>
					</tr>
				</table>
				<br> 
				
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