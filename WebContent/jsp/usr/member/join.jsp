<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle }</h1>

<div>
	<form action="doJoin" method="POST">
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
		<div>가입</div>
		<div>
			<input type="submit" value="가입">
			<button type="button" onclick="history.back()">뒤로가기</button>
		</div>
		<hr>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>