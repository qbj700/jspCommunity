<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="로그인" />

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>${pageTitle }</title>

<!-- 반응형 필수 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/common.css" />
<script src="${pageContext.request.contextPath}/static/common.js" defer></script>
</head>

<body>
	
	<section class="login-page flex flex-ai-c flex-jc-c">
		<div class="login-page__frame">
			<div class="frame__content-1">
				<div class="login-logo-box">
					<a href="../home/main" class="login-logo"> <span>MODUMOA
							.</span> <i class="fas fa-asterisk"></i>
					</a>
				</div>

				<div class="login-form">
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
					<form autocomplete="off" action="doLogin" method="POST" onsubmit="DoLoginForm__submit(this); return false;">
						<div>로그인 아이디</div>
						<div>
							<input type="text" name="loginId" placeholder="아이디를 입력해주세요."
								maxlength="50">
						</div>


						<div>로그인 비밀번호</div>
						<div>
							<input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요."
								maxlength="50">
						</div>

						<div class="login-submit-box">
							<input class="login-submit" type="submit" value="로그인">
						</div>

					</form>
				</div>

			</div>
		</div>
	</section>

</body>
</html>