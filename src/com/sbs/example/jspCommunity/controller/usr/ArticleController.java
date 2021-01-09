package com.sbs.example.jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId);

		req.setAttribute("articles", articles);

		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Article article = articleService.getForPrintArticleById(id);

		req.setAttribute("article", article);

		return "usr/article/detail";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int memberId = Integer.parseInt(req.getParameter("memberId"));

		int articleId = articleService.addArticle(title, body, memberId, boardId);

		req.setAttribute("articleId", articleId);

		return "usr/article/doWrite";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int memberId = Integer.parseInt(req.getParameter("memberId"));

		String message = "";

		Article article = articleService.getArticleById(id);

		if (article == null) {
			message = id + "번 게시물은 존재하지 않습니다.";
		}

		if (article != null) {
			if (article.memberId != memberId) {
				message = "수정할 권한이 없습니다.";
			}

			if (article.memberId == memberId) {
				articleService.modify(title, body, id);
				message = id + "번 게시물이 수정되었습니다.";
			}
		}

		req.setAttribute("message", message);

		return "usr/article/doModify";
	}

}