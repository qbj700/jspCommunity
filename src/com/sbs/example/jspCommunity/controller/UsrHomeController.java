package com.sbs.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.MemberService;
import com.sbs.example.jspCommunity.util.Util;

public class UsrHomeController extends Controller {
	private ArticleService articleService;
	
	public UsrHomeController() {
		articleService = Container.articleService;
	}
	
	public String showMain(HttpServletRequest req, HttpServletResponse resp) {
		String boardCode = "meme";
		
		List<Article> articles = articleService.getForPrintArticlesByBoardCode(boardCode);

		req.setAttribute("articles", articles);
		return "usr/home/main";
	}
}
