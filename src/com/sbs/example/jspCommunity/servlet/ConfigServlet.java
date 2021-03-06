package com.sbs.example.jspCommunity.servlet;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.util.Util;

//loadOnStratUp = 프로그램이 실행되면 자동으로 초기화 진행
@WebServlet(name = "loadAppConfig", urlPatterns = { "/loadConfig" }, loadOnStartup = 1)
public class ConfigServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ServletContext context = getServletContext();
		InputStream inStream = context.getResourceAsStream("/META-INF/config.json");
		Map<String, Object> configMap = Util.getJsonMapFromFile(inStream);

		String gmailId = (String) configMap.get("gmailId");
		String gmailPw = (String) configMap.get("gmailPw");

		EmailService emailService = Container.emailService;
		emailService.init(gmailId, gmailPw, "jspCommunity", "jspCommunity");

		//임시 메일발송 테스트용 코드
		//emailService.send("qbj700@gmail.com", "메일테스트", "내용");
	}
}
