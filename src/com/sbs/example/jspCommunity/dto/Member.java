package com.sbs.example.jspCommunity.dto;

import java.util.Map;

public class Member {
	public int id;
	public String regDate;
	public String loginId;
	public String loginPw;
	public String name;
	public String nickname;
	public String email;
	public String cellphoneNo;
	public int authLevel;

	public Member(Map<String, Object> memberMap) {
		this.id = (int) memberMap.get("id");
		this.regDate = (String) memberMap.get("regDate");
		this.loginId = (String) memberMap.get("loginId");
		this.loginPw = (String) memberMap.get("loginPw");
		this.name = (String) memberMap.get("name");
		this.nickname = (String) memberMap.get("nickname");
		this.email = (String) memberMap.get("email");
		this.cellphoneNo = (String) memberMap.get("cellphoneNo");
		this.authLevel = (int) memberMap.get("authLevel");
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", regDate=" + regDate + ", loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + ", nickname=" + nickname + ", email=" + email + ", cellphoneNo=" + cellphoneNo + ", authLevel=" + authLevel + "]";
	}

}
