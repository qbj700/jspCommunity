package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.util.MysqlUtil;
import com.sbs.example.jspCommunity.util.SecSql;

public class ArticleDao {

	public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
		sql.append(", IFNULL(SUM(IF (L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF (L.point < 0, L.point * -1, 0)), 0) AS extra__dislikeOnlyPoint");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.id = L.relId");
		
		if (boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}

		if (searchKeywordType != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			}
			if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			}
			if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword, searchKeyword);
			}
		}

		sql.append("GROUP BY A.id");
		sql.append("ORDER BY A.id DESC");
		
		if ( limitCount != -1) {
			sql.append("LIMIT ?, ?", limitStart, limitCount);
		}

		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleListMap) {

			articles.add(new Article(articleMap));

		}

		return articles;
	}

	public Article getForPrintArticleById(int id) {

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
		sql.append(", IFNULL(SUM(IF (L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF (L.point < 0, L.point * -1, 0)), 0) AS extra__dislikeOnlyPoint");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.id = L.relId");
		sql.append("WHERE A.id = ?", id);
		sql.append("GROUP BY A.id");

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	public int write(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", args.get("title"));
		sql.append(", body = ?", args.get("body"));
		sql.append(", memberId = ?", args.get("memberId"));
		sql.append(", boardId = ?", args.get("boardId"));

		return MysqlUtil.insert(sql);

	}

	public Article getArticleById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW(),");

		boolean needToUpdate = false;

		if (args.get("title") != null) {
			needToUpdate = true;
			sql.append("title = ?,", args.get("title"));
		}
		if (args.get("body") != null) {
			needToUpdate = true;
			sql.append("body = ?", args.get("body"));
		}

		if (needToUpdate == false) {
			return 0;
		}

		sql.append("WHERE id = ?", args.get("id"));

		return MysqlUtil.update(sql);
	}

	public void delete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		MysqlUtil.delete(sql);
	}

	public Board getBoardByBoardId(int boardId) {

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM board");
		sql.append("WHERE id = ?", boardId);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		return new Board(boardMap);

	}

	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		}

		if (searchKeywordType != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			}
			if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			}
			if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,searchKeyword);
			}
		}

		return MysqlUtil.selectRowIntValue(sql);
	}

	public List<Article> getForPrintArticlesByBoardCode(String boardCode) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("WHERE B.code = ?", boardCode);
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT 4");

		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleListMap) {

			articles.add(new Article(articleMap));
		}

		return articles;
	}

}
