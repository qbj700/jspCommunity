<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="메인화면" />

<%@ include file="../../part/head.jspf"%>

<div class="con-min-width">
	<div class="con padding-0-10">
		<div class="img-box">
			<img src="https://i.ibb.co/jyyDvcB/image.jpg"/>
		</div>
	</div>
</div>

<div class="title-bar padding-0-10 con-min-width">
	<h1 class="con flex">
		<span>
			<i class="fas fa-trophy"></i>
		</span>
		<span style="margin-left:10px;">WEEKLY BEST MEME</span>
		<div class="flex-grow-1"></div>
		<span class="flex-jc-e"><a href="../article/list?boardId=3">더보기</a></span>
	</h1>
</div>

<div class="con-min-width">
	<div class="con padding-0-10">
		<div class="latest-list-box flex flex-jc-sb padding-10-10">
			<c:forEach var="article" items="${articles }">
	
				<div class="article-body-box hover-link">
					<a href="../article/detail?id=${article.id}&listUrl=${encodedCurrentUrl}" >
						<script type="text/x-template">${article.body}</script>
						<div class="toast-ui-viewer"></div>
					</a>
					<a href="../article/detail?id=${article.id}&listUrl=${encodedCurrentUrl}" >
						<div>제목 : ${article.title}</div>
					</a>
					<div>작성자 : ${article.extra__writer}</div>
				</div>
							
			</c:forEach>
		</div>	
	</div>
</div>




<%@ include file="../../part/foot.jspf"%>