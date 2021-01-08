package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.util.MysqlUtil;
import com.sbs.example.jspCommunity.util.SecSql;

@WebServlet("/usr/member/list")
public class MemberListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");

		List<Member> members = Container.memberService.getForPrintMembers();

		MysqlUtil.closeConnection();

		req.setAttribute("members", members);

		req.getRequestDispatcher("/jsp/usr/member/list.jsp").forward(req, resp);
	}
}
