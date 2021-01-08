package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.util.MysqlUtil;
import com.sbs.example.jspCommunity.util.SecSql;

public class MemberDao {

	public List<Member> getForPrintMembers() {
		List<Member> members = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("ORDER BY M.id DESC");

		List<Map<String, Object>> memberListMap = MysqlUtil.selectRows(sql);

		for (Map<String, Object> memberMap : memberListMap) {

			members.add(new Member(memberMap));

		}

		return members;
	}

}
