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

@WebServlet("/usr/article/doModify")
public class articleModifyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");

		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		doModifyArticle(id, title, body);

		MysqlUtil.closeConnection();
		
		resp.getWriter().append(String.format("<h1>" + "%d번 게시물이 수정되었습니다." + "</h1>", id));
	}

	private void doModifyArticle(int id, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET title = ?,", title);
		sql.append("body = ?,", body);
		sql.append("updateDate = NOW()");
		sql.append("WHERE id = ?", id);

		MysqlUtil.update(sql);
	}

	private int doWriteArticle(String title, String body, String memberId, String boardId) {
		
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("body = ?,", body);
		sql.append("memberId = ?,", memberId);
		sql.append("boardId = ?", boardId);

		return MysqlUtil.insert(sql);
	}
}
