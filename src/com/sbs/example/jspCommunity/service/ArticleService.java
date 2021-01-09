package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		return articleDao.getForPrintArticlesByBoardId(boardId);

	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public int addArticle(String title, String body, int memberId, int boardId) {
		return articleDao.addArticle(title, body, memberId, boardId);

	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void modify(String title, String body, int id) {
		articleDao.modify(title, body, id);
	};

}
