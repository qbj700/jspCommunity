package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.util.MysqlUtil;
import com.sbs.example.jspCommunity.util.SecSql;

@WebServlet("/usr/article/list")
public class articleListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");

		String boardCode = req.getParameter("boardCode");
		Map<String, Object> boardMap = getBoardByBoardCode(boardCode);
		List<Map<String, Object>> articleListMap = getArticlesByBoardId((int) boardMap.get("id"));

		MysqlUtil.closeConnection();

		req.setAttribute("boardMap", boardMap);
		req.setAttribute("articleListMap", articleListMap);

		req.getRequestDispatcher("/jsp/usr/article/list.jsp").forward(req, resp);
	}

	private Map<String, Object> getBoardByBoardCode(String boardCode) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM board");
		sql.append("WHERE `code` = ?", boardCode);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		return boardMap;
	}

	private List<Map<String, Object>> getArticlesByBoardId(int boardId) {

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

		return articleListMap;
	}
}
