<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복체크</title>

<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style type="text/css">
#wrap {
	width: 490px;
	text-align: center;
	margin: 0 auto 0 auto;
}

#chk {
	text-align: center;
}

#cancelBtn {
	visibility: visible;
}

#useBtn {
	visibility: hidden;
}
</style>
<script>
//회원가입창의 아이디 입력란의 값을 가져온다.
function pValue(){
document.getElementById("userId").value = opener.document.userInfo.loginId.value;
}

//아이디 중복체크
function enterEvent() {
	if (window.event.keyCode == 13){
		idCheck();
		}
};
function idCheck(){

var loginId = document.getElementById("userId").value;

function callback(data){
	if ( data.resultCode.substr(0, 2) == "S-") {
		$("#cancelBtn").css('visibility', 'hidden');
	   	$("#useBtn").css('visibility', 'visible');
	   	alert(data.msg);
	}
	else {
		$("#cancelBtn").css('visibility', 'visible');
	   	$("#useBtn").css('visibility', 'hidden');
	   	alert(data.msg);
	}
}

if (!loginId) {
 alert("아이디를 입력하지 않았습니다.");
 return false;
} 
else if((loginId < "0" || loginId > "9") && (loginId < "A" || loginId > "Z") && (loginId < "a" || loginId > "z")){ 
 alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
 return false;
}
else
{
 $.post("getIdCheck", {loginId:loginId}, callback, "json");
}

}



//사용하기 클릭 시 부모창으로 값 전달 
function sendCheckValue(){
// 중복체크 결과인 idCheck 값을 전달한다.
opener.document.userInfo.idDuplication.value ="idCheck";
// 회원가입 화면의 ID입력란에 값을 전달
opener.document.userInfo.loginId.value = document.getElementById("userId").value;

if (opener != null) {
 opener.chkForm = null;
 self.close();
}    
}    
</script>
</head>
<body onload="pValue()">
	<div id="wrap">
		<br> <b><font size="4" color="gray">아이디 중복체크</font></b>
		<hr size="1" width="460">
		<br>
		<div id="chk">
			<form id="checkForm" onsubmit="return false;">
				<input type="text" name="idinput" id="userId" onkeypress="enterEvent()">
				<input type="button" value="중복확인" onclick="idCheck()">
			</form>
			<div id="msg"></div>
			<br> <input id="cancelBtn" type="button" value="취소"
				onclick="window.close()"><br> <input id="useBtn"
				type="button" value="사용하기" onclick="sendCheckValue()">
		</div>
	</div>
</body>
</html>