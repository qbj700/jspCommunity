<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원정보 수정" />

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>${pageTitle }</title>

<!-- 반응형 필수 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/common.css" />
<script src="${pageContext.request.contextPath}/static/common.js" defer></script>
</head>

<body>
	<section class="join-page flex flex-ai-c flex-jc-c">
		<div class="join-page__frame">
			<div class="frame__content-1">



				<div class="login-logo-box">
					<a href="../home/main" class="login-logo"> <span>MODUMOA
							.</span> <i class="fas fa-asterisk"></i>
					</a>
				</div>


				<div class="title-bar con-min-width">
					<h1 class="con">
						<span>${pageTitle }</span>
					</h1>
				</div>

				<div class="join-form">

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

							if (!form.email.value.trim()) {
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
								<td class="inputedId">아이디 : ${loginedMember.loginId}</td>
							</tr>

							<tr>

								<td>
									<div class="input-form">
										<input type="password" name="loginPw" maxlength="50"
											autocomplete="off"> <label
											class="label-name" for="name"> <span
											class="content-name"> 로그인 비밀번호 </span>
										</label>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<div class="input-form">
										<input type="password" name="loginPwConfirm" maxlength="50"
											autocomplete="off"> <label
											class="label-name" for="name"> <span
											class="content-name"> 로그인 비밀번호 확인 </span>
										</label>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<div class="input-form">
										<input type="" text"" name="name" maxlength="50"
											autocomplete="off" value="${loginedMember.name}" required> <label
											class="label-name" for="name"> <span
											class="content-name"> 이름 </span>
										</label>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="input-form">
										<input type="" text"" name="nickname" maxlength="50"
											autocomplete="off" value="${loginedMember.nickname}" required> <label
											class="label-name" for="name"> <span
											class="content-name"> 별명 </span>
										</label>
									</div>
								</td>
							</tr>

							<tr>
								<td class="flex flex-ai-c">
									<div class="input-form">
										<input type="email" name="email" maxlength="50"
											autocomplete="off" value="${loginedMember.email}" required> <label
											class="label-name" for="name"> <span
											class="content-name"> 이메일 </span>
										</label>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="input-form">
										<input type="tel" name="cellphoneNo" maxlength="50"
											autocomplete="off" value="${loginedMember.cellphoneNo}" required> <label
											class="label-name" for="name"> <span
											class="content-name"> 휴대전화 </span>
										</label>
									</div>
								</td>
							</tr>
						</table>

						<br>

						<div class="article-btn-box">
							<div class="btn-wrap">
								<input type="submit" class="btn btn-primary" value="수정">
								<button type="button" class="btn btn-info"
									onclick="history.back()">뒤로가기</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>