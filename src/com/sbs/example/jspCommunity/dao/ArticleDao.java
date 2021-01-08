package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.util.MysqlUtil;
import com.sbs.example.jspCommunity.util.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT article.*, member.name AS extra__writer");
		sql.append(",board.name AS extra__boardName");
		sql.append(",board.code AS extra__boardCode");
		sql.append("FROM article");
		sql.append("INNER JOIN `member`");
		sql.append("ON article.memberId = member.id");
		sql.append("INNER JOIN `board`");
		sql.append("ON article.boardId = board.id");
		if (boardId != 0) {
			sql.append("WHERE article.boardId = ?", boardId);
		}
		sql.append("ORDER BY article.id DESC");

		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleListMap) {

			articles.add(new Article(articleMap));

		}

		return articles;
	}

	public Article getForPrintArticleById(int id) {

		SecSql sql = new SecSql();
		sql.append("SELECT article.*,member.name AS extra__writer");
		sql.append("FROM article");
		sql.append("INNER JOIN member");
		sql.append("ON article.id = ? AND article.memberId = member.id", id);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		return new Article(articleMap);
	}

}
