package com.KoreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	
	public Article(String title, String body, int id, String regDate) {
		this(title, body, id, regDate, 0);
	}
	
	public Article(String title, String body, int id, String regDate, int hit) {
		this.title = title;
		this.body = body;
		this.id = id;
		this.regDate = regDate;
		this.hit = hit;
	}
	
	
	public void increaseHit() {
		hit++;
	}
}
