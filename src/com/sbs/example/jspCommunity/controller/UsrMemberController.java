package com.sbs.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.ResultData;
import com.sbs.example.jspCommunity.service.MemberService;
import com.sbs.example.jspCommunity.util.Util;

public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();

		req.setAttribute("members", members);

		return "usr/member/list";
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String email = email1 + "@" + email2;
		String cellphoneNo = req.getParameter("cellphoneNo");

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			req.setAttribute("alertMsg", "이미 사용중인 로그인 아이디입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("cellphoneNo", cellphoneNo);

		int id = memberService.join(joinArgs);
		Container.attrService.setValue("member__" + id + "__extra__isValidPassword", "1", "DATE_ADD(NOW(), INTERVAL 90 DAY)");

		// 가입축하 이메일 발송
		// memberService.sendCongratulationsEmail(name, nickname, email);
		
		req.setAttribute("alertMsg", id + "번 회원이 생성되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/login";
	}
	
	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "존재하지 않는 아이디입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		HttpSession session = req.getSession();
		session.setAttribute("loginedMemberId", member.getId());
		
		String value = Container.attrService.getValue("member__" + member.getId() + "__extra__isUsingTempPassword");

		req.setAttribute("alertMsg", String.format("%s님 환영합니다.", member.getNickname()));
		
		if (value.equals("1")) {
			req.setAttribute("alertMsg2", "임시 비밀번호를 사용중입니다. 비밀번호 변경후 이용해주시기 바랍니다.");
		}
		
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
		
		
	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");

		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}
	
	public String showIdCheckForm(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/idCheckForm";
	}

	public String getIdCheck(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		String resultCode = null;
		String msg = null;

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인아이디 입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용가능한 로그인아이디 입니다.";
		}

		req.setAttribute("data", new ResultData(resultCode, msg, "loginId", loginId));

		return "common/json";

	}

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("alertMsg", String.format("로그인 아이디는 %s 입니다.", member.getLoginId()));
		req.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String email = req.getParameter("email");

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getEmail().equals(email) == false) {
			req.setAttribute("alertMsg", "이메일 주소를 정확히 입력해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);
		Container.attrService.setValue("member__" + member.getId() + "__extra__isValidPassword", "1", "DATE_ADD(NOW(), INTERVAL 90 DAY)");

		if (sendTempLoginPwToEmailRs.isFail()) {
			req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
		req.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		Member member = (Member) req.getAttribute("loginedMember");
		String[] emailBits = member.getEmail().split("@");
		String email1 = emailBits[0];
		String email2 = emailBits[1];
		
		req.setAttribute("email1", email1);
		req.setAttribute("email2", email2);
		
		return "usr/member/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		
		String loginPw = req.getParameter("loginPwReal");
		
		if ( loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}
		
		if ( loginPw != null && loginPw.length() > 0) {
			Container.attrService.remove("member__" + loginedMemberId + "__extra__isUsingTempPassword");
			Container.attrService.setValue("member__" + loginedMemberId + "__extra__isValidPassword", "1", "DATE_ADD");
		}
		
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String email = email1 + "@" + email2;
		String cellphoneNo = req.getParameter("cellphoneNo");
		
		Map<String, Object> modifyParam	= new HashMap<>();
		
		modifyParam.put("loginPw", loginPw);
		modifyParam.put("name", name);
		modifyParam.put("nickname", nickname);
		modifyParam.put("email", email);
		modifyParam.put("cellphoneNo", cellphoneNo);
		modifyParam.put("id", loginedMemberId);
		
		memberService.modify(modifyParam);
		
		req.setAttribute("alertMsg", "회원정보가 수정되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}
}
