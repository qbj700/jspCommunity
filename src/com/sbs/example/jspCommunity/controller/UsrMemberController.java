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

public class UsrMemberController extends Controller {
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

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "로그인아이디를 입력해주세요.");
		}

		String loginPw = req.getParameter("loginPwReal");

		if (Util.isEmpty(loginPw)) {
			return msgAndBack(req, "로그인비번을 입력해주세요.");
		}

		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요.");
		}

		String nickname = req.getParameter("nickname");

		if (Util.isEmpty(nickname)) {
			return msgAndBack(req, "별명을 입력해주세요.");
		}

		String email1 = req.getParameter("email1");

		if (Util.isEmpty(email1)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		String email2 = req.getParameter("email2");

		if (Util.isEmpty(email2)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		String email = email1 + "@" + email2;

		String cellphoneNo = req.getParameter("cellphoneNo");

		if (Util.isEmpty(cellphoneNo)) {
			return msgAndBack(req, "휴대전화번호를 입력해주세요.");
		}

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return msgAndBack(req, "이미 사용중인 로그인 아이디입니다.");
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

		return msgAndReplace(req, id + "번 회원이 생성되었습니다.", "../home/main");
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/login";
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "로그인아이디를 입력해주세요.");
		}

		String loginPw = req.getParameter("loginPwReal");

		if (Util.isEmpty(loginPw)) {
			return msgAndBack(req, "로그인비밀번호를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return msgAndBack(req, "비밀번호가 일치하지 않습니다.");
		}

		HttpSession session = req.getSession();
		session.setAttribute("loginedMemberId", member.getId());

		boolean isUsingTempPassword = memberService.getIsUsingTempPassword(member.getId());

		String alertMsg = String.format("%s님 환영합니다.", member.getNickname());
		String replaceUrl = "../home/main";

		if (Util.isEmpty(req.getParameter("afterLoginUrl")) == false) {
			replaceUrl = req.getParameter("afterLoginUrl");
		}

		if (isUsingTempPassword) {
			alertMsg = String.format("%s님은 현재 임시 비밀번호를 사용중입니다. 변경 후 이용해주세요.", member.getNickname());
			replaceUrl = "../member/modify";
		}

		return msgAndReplace(req, alertMsg, replaceUrl);
	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");

		return msgAndReplace(req, "로그아웃 되었습니다.", "../home/main");
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

		return json(req, new ResultData(resultCode, msg, "loginId", loginId));
	}

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요.");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		return msgAndReplace(req, String.format("로그인 아이디는 %s 입니다.", member.getLoginId()), "../member/login");
	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "로그인 아이디를 입력해주세요.");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		if (member.getEmail().equals(email) == false) {
			return msgAndBack(req, "이메일 주소를 정확히 입력해주세요.");
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);
		Container.attrService.setValue("member__" + member.getId() + "__extra__isValidPassword", "1", "DATE_ADD(NOW(), INTERVAL 90 DAY)");

		if (sendTempLoginPwToEmailRs.isFail()) {
			return msgAndBack(req, sendTempLoginPwToEmailRs.getMsg());
		}

		return msgAndReplace(req, sendTempLoginPwToEmailRs.getMsg(), "../member/login");
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
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		String loginPw = req.getParameter("loginPwReal");

		if (loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}

		if (loginPw != null && loginPw.length() > 0) {
			Container.attrService.remove("member__" + loginedMemberId + "__extra__isUsingTempPassword");
			Container.attrService.setValue("member__" + loginedMemberId + "__extra__isValidPassword", "1", "DATE_ADD");
		}

		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요.");
		}

		String nickname = req.getParameter("nickname");

		if (Util.isEmpty(nickname)) {
			return msgAndBack(req, "별명을 입력해주세요.");
		}

		String email1 = req.getParameter("email1");

		if (Util.isEmpty(email1)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		String email2 = req.getParameter("email2");

		if (Util.isEmpty(email2)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		String email = email1 + "@" + email2;

		String cellphoneNo = req.getParameter("cellphoneNo");

		if (Util.isEmpty(cellphoneNo)) {
			return msgAndBack(req, "휴대전화번호를 입력해주세요.");
		}

		Map<String, Object> modifyParam = new HashMap<>();

		modifyParam.put("loginPw", loginPw);
		modifyParam.put("name", name);
		modifyParam.put("nickname", nickname);
		modifyParam.put("email", email);
		modifyParam.put("cellphoneNo", cellphoneNo);
		modifyParam.put("id", loginedMemberId);

		memberService.modify(modifyParam);

		return msgAndReplace(req, "회원정보가 수정되었습니다.", "../home/main");
	}
}
