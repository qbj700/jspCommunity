<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<script>
var alertMsg = '<%=request.getAttribute("alertMsg")%>'.trim();

if ( alertMsg ) {
	alert(alertMsg);
}

var alertMsg2 = '<%=request.getAttribute("alertMsg2")%>'.trim();

if ( alertMsg2 != "null") {
	alert(alertMsg2);
}

var historyBack = '<%=request.getAttribute("historyBack")%>' == 'true';

if ( historyBack ){
	history.back();
}

var replaceUrl = '<%=request.getAttribute("replaceUrl")%>';

if (replaceUrl) {
	location.replace(replaceUrl);
}
</script>
