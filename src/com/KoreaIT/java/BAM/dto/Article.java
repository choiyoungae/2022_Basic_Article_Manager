package com.KoreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public int memberId;
	
	
	public Article(String title, String body, int id, String regDate, int memberId) {
		this(title, body, id, regDate, 0, memberId);
	}
	
	public Article(String title, String body, int id, String regDate, int hit, int memberId) {
		this.title = title;
		this.body = body;
		this.id = id;
		this.regDate = regDate;
		this.hit = hit;
		this.memberId = memberId;
	}
	
	
	public void increaseHit() {
		hit++;
	}
}
