package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.usr.ArticleController;
import com.sbs.example.jspCommunity.controller.usr.MemberController;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.util.MysqlUtil;
import com.sbs.example.jspCommunity.util.SecSql;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 5) {
			resp.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}

		String controllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];

		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jspCommunity");

		String jspPath = null;

		if (controllerName.equals("member")) {
			MemberController memberController = Container.memberController;

			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(req, resp);
			}
		} else if (controllerName.equals("article")) {
			ArticleController articleController = Container.articleController;

			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(req, resp);
			}
			if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(req, resp);
			}
			if (actionMethodName.equals("write")) {
				jspPath = articleController.doWrite(req, resp);
			}
			if (actionMethodName.equals("doWrite")) {
				jspPath = articleController.write(req, resp);
			}
			if (actionMethodName.equals("modify")) {
				jspPath = articleController.doModify(req, resp);
			}
			if (actionMethodName.equals("delete")) {
				jspPath = articleController.doDelete(req, resp);
			}
		}

		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}
}
