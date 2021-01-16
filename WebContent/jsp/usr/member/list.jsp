<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Member"%>
<%
	List<Member> members = (List<Member>) request.getAttribute("members");
String pageTitle = "회원 리스트";
%>

<%@ include file="../../part/head.jspf"%>

	<h1><%=pageTitle %></h1>
	<%
		for (Member member : members) {
	%>
	<div>
		번호 :
		<%=member.getId()%>
		<br /> 이름 :
		<%=member.getName()%>
		<br /> 닉네임 :
		<%=member.getNickname()%>
		<hr />
	</div>
	<%
		}
	%>
<%@ include file="../../part/foot.jspf"%>