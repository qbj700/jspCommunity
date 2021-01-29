package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.AdmMemberController;
import com.sbs.example.jspCommunity.controller.UsrArticleController;
import com.sbs.example.jspCommunity.controller.UsrHomeController;
import com.sbs.example.jspCommunity.controller.UsrMemberController;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.AttrDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.AttrService;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static UsrMemberController usrMemberController;
	public static AdmMemberController admMemberController;
	public static UsrArticleController usrArticleController;
	public static UsrHomeController usrHomeController;
	public static EmailService emailService;
	public static AttrService attrService;
	public static AttrDao attrDao;

	static {
		attrDao = new AttrDao();
		articleDao = new ArticleDao();
		memberDao = new MemberDao();

		attrService = new AttrService();
		emailService = new EmailService();
		articleService = new ArticleService();
		memberService = new MemberService();

		usrArticleController = new UsrArticleController();
		usrMemberController = new UsrMemberController();
		admMemberController = new AdmMemberController();
		usrHomeController = new UsrHomeController();
	}
}
