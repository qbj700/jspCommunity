package com.sbs.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.service.ArticleService;

public class UsrArticleController {
	private ArticleService articleService;

	public UsrArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));

		Board board = articleService.getBoardByBoardId(boardId);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId);

		req.setAttribute("board", board);
		req.setAttribute("articles", articles);

		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("article", article);

		return "usr/article/detail";
	}

	public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int boardId = Integer.parseInt(req.getParameter("boardId"));

		Board board = articleService.getBoardByBoardId(boardId);
		req.setAttribute("board", board);

		return "usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int memberId = (int)session.getAttribute("loginedMemberId");

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("memberId", memberId);
		writeArgs.put("boardId", boardId);
		writeArgs.put("title", title);
		writeArgs.put("body", body);

		int newArticleId = articleService.write(writeArgs);

		req.setAttribute("alertMsg", newArticleId + "번 게시물이 생성되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", newArticleId));
		return "common/redirect";
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int id = Integer.parseInt(req.getParameter("id"));
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");

		Article article = articleService.getArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.getMemberId() != loginedMemberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("article", article);

		return "usr/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int id = Integer.parseInt(req.getParameter("id"));
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");

		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.getMemberId() != loginedMemberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String title = req.getParameter("title");
		String body = req.getParameter("body");

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("id", id);
		writeArgs.put("title", title);
		writeArgs.put("body", body);

		articleService.modify(writeArgs);

		req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int id = Integer.parseInt(req.getParameter("id"));
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");

		Article article = articleService.getArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.getMemberId() != loginedMemberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.delete(id);

		req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=%d", article.getBoardId()));
		return "common/redirect";
	}

}
