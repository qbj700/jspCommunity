package com.sbs.example.jspCommunity.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hitsCount;
	
	private Map<String, Object> extra;

	private String extra__writer;
	private String extra__boardName;
	private String extra__boardCode;
	private int extra__likePoint;
	private int extra__likeOnlyPoint;
	private int extra__dislikeOnlyPoint;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.memberId = (int) articleMap.get("memberId");
		this.boardId = (int) articleMap.get("boardId");
		this.hitsCount = (int) articleMap.get("hitsCount");

		if (articleMap.containsKey("extra__writer")) {
			this.extra__writer = (String) articleMap.get("extra__writer");
		}
		if (articleMap.containsKey("extra__boardName")) {
			this.extra__boardName = (String) articleMap.get("extra__boardName");
		}
		if (articleMap.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String) articleMap.get("extra__boardCode");
		}
		if (articleMap.containsKey("extra__likePoint")) {
			this.extra__likePoint = (int) articleMap.get("extra__likePoint");
		}
		if (articleMap.containsKey("extra__likeOnlyPoint")) {
			this.extra__likeOnlyPoint = (int) articleMap.get("extra__likeOnlyPoint");
		}
		if (articleMap.containsKey("extra__dislikeOnlyPoint")) {
			this.extra__dislikeOnlyPoint = (int) articleMap.get("extra__dislikeOnlyPoint");
		}

		this.extra = new LinkedHashMap<>();
	}
}
