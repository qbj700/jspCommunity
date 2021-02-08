<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="로그인 아이디 찾기" />

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
				<div class="pageTitle"><h1>아이디 찾기</h1></div>

				<div class="login-form">
				<script>
		let DoFindLoginIdForm__submited = false;
		function DoFindLoginIdForm__submit(form) {
			if (DoFindLoginIdForm__submited) {
				alert('처리중입니다.');
				return;
			}

			form.name.value = form.name.value.trim();

			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.name.focus();

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				alert('이메일을 입력해주세요.');
				form.email.focus();

				return;
			}
			
			form.submit();
			DoFindLoginIdForm__submited = true;
		}
	</script>
					<form autocomplete="off" action="doFindLoginId" method="POST" onsubmit="DoFindLoginIdForm__submit(this); return false;">
						
						<div class="login-form__section-1">
							<input type="text" name="name" autocomplete="off" required>
							<label class="label-name" for="name"><span
								class="content-name"> 이름 </span></label>
						</div>


						<div class="login-form__section-1">
							<input type="email" name="email" autocomplete="off" required>
							<label class="label-name" for="name"><span
								class="content-name"> 회원정보에 등록된 이메일 </span></label>
						</div>

						<div class="login-submit-box">
							<input class="login-submit" type="submit" value="로그인 아이디 찾기">
						</div>
						
						<div class="join-link">아직 회원이 아니신가요? <a href="join">회원가입하기</a></div>
						
						<div class="join-link">혹시 비밀번호를 잊으셨나요? <a href="findLoginPw">비밀번호 찾기</a></div>

					</form>
				</div>

			</div>
		</div>
	</section>

</body>
</html>