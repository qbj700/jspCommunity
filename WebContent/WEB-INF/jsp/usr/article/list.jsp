<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name} 게시판 게시물 리스트" />

<%@ include file="../../part/head.jspf"%>


<div class="title-bar con-min-width">
	<h1 class="con">
		<span><i class="fas fa-list"></i></span> <span>${pageTitle }</span>
	</h1>
</div>

<div class="con-min-width">
	<div class="con">
		<div>
			<script>
				let DoSearchForm__submited = false;
				function DoSearchForm__submit(form) {
					form.searchKeyword.value = form.searchKeyword.value.trim();

					if (DoSearchForm__submited) {
						alert('처리중입니다.');
						return;
					}
					
					form.submit();
					DoSearchForm__submited = true;
				}
			</script>
			<form action="" onsubmit="DoSearchForm__submit(this); return false;">
				<input type="hidden" name="boardId" value="${param.boardId}" /> <select
					name="searchKeywordType">
					<option value="titleAndBody">제목+본문</option>
					<option value="title">제목</option>
					<option value="body">본문</option>
				</select>
				<script>
					const param__searchKeywordType = '${param.searchKeywordType}';

					if (param__searchKeywordType) {
						$('select[name="searchKeywordType"]').val(
								param__searchKeywordType);
					}
				</script>
				<input value="${param.searchKeyword}" type="text"
					name="searchKeyword" placeholder="검색어를 입력해주세요." /> <input
					type="submit" value="검색" />
			</form>
		</div>
	</div>
</div>

<div class="con-min-width">
	<div class="con">
		<br />
		<div>총 게시물 수 : ${totalCount} 개</div>
		<br />
	</div>
</div>


<div class="article-list-box con-min-width">
	<div class="con">
		<table>
			<colgroup>
				<col width="100">
				<col width="200">
				<col width="150">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>작성자</th>
					<th>제목</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr class="hover-link">
						<td><span class="article-list-box__id">${article.id}</span></td>
						<td><span class="article-list-box__reg-date">${article.regDate}</span>
						</td>
						<td><span class="article-list-box__writer">${article.extra__writer}</span>
						</td>
						<td><a href="detail?id=${article.id}"
							class="article-list-box__title article-list-box__title--pc">${article.title}</a>
						</td>
						<td class="visible-sm-down">
							<div class="flex">
								<span class="article-list-box__id article-list-box__id--mobile">3</span>
								<a href="detail?id=${article.id}"
									class="article-list-box__title article-list-box__title--mobile flex-grow-1">${article.title}</a>
							</div>
							<div class="flex">
								<span
									class="article-list-box__writer article-list-box__writer--mobile">${article.extra__writer}</span>
								<span>|</span> <span
									class="article-list-box__reg-date article-list-box__reg-date--mobile">${article.regDate}</span>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="article-btn-box con-min-width">
	<div class="con btn-wrap">
		<a class="btn btn-primary" href="write?boardId=${param.boardId}">글쓰기</a>
	</div>
</div>

<br />
<br />

<style>
.red {
	color: red;
}
</style>
<div class="con-min-width">
	<div class="con flex flex-jc-c">
		<c:set var="aUrl"
			value="?page=1&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
		<a href="${aUrl}">첫 페이지 &nbsp;</a>

		<c:if test="${pageBoxStartBeforeBtnNeedToShow}">
			<c:set var="aUrl"
				value="?page=${pageBoxStartBeforePage}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a href="${aUrl}">◀</a>
		</c:if>
		<c:forEach var="i" begin="${pageBoxStartPage}" end="${pageBoxEndPage}"
			step="1">
			<c:set var="aClass" value="${page == i ? 'red' : '' }" />
			<c:set var="aUrl"
				value="?page=${i}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a class="${aClass}" href="${aUrl}">${i}</a>
		</c:forEach>
		<c:if test="${pageBoxEndAfterBtnNeedToShow}">
			<c:set var="aUrl"
				value="?page=${pageBoxEndAfterPage}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a href="${aUrl}">▶</a>
		</c:if>

		<c:set var="aUrl"
			value="?page=${totalPage}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
		<a href="${aUrl}">&nbsp; 끝 페이지</a>
	</div>
</div>
<%@ include file="../../part/foot.jspf"%>