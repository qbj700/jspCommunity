package com.sbs.example.jspCommunity.controller.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
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

		int boardId = Integer.parseInt(req.getParameter("boardId"));

		Board board = articleService.getBoardByBoardId(boardId);
		req.setAttribute("board", board);

		return "usr/article/write";
	}
	
	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int memberId = Integer.parseInt(req.getParameter("memberId"));

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

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		int memberId = Integer.parseInt(req.getParameter("memberId"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.memberId != memberId) {
			req.setAttribute("alertMsg", "수정할 권한이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		req.setAttribute("id", id);

		return "usr/article/modify";
	}
	
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int id = Integer.parseInt(req.getParameter("id"));
		
		articleService.modify(title, body, id);
		req.setAttribute("id", id);
		
		
		return "usr/article/modifyResult";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		int memberId = Integer.parseInt(req.getParameter("memberId"));

		Article article = articleService.getArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.memberId != memberId) {
			req.setAttribute("alertMsg", "삭제할 권한이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		articleService.delete(id);
		req.setAttribute("id", id);

		return "usr/article/delete";
	}

}
