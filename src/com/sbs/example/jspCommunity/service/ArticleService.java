package com.sbs.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public int write(Map<String, Object> args) {
		return articleDao.write(args);

	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void modify(Map<String, Object> args) {
		articleDao.modify(args);
	}

	public void delete(int id) {
		articleDao.delete(id);

	}

	public Board getBoardByBoardId(int boardId) {
		return articleDao.getBoardByBoardId(boardId);
	}

	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId,int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintArticlesByBoardId(boardId,limitStart,limitCount, searchKeywordType, searchKeyword);
	};

}
