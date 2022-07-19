package com.KoreaIT.java.BAM.dto;

public class Article {
	public String title;
	public String body;
	public int articleNumber;
	public String regDate;
	public int hit;
	
	public Article(String title, String body, int articleNumber, String regDate) {
		this(title, body, articleNumber, regDate, 0);
	}
	
	public Article(String title, String body, int articleNumber, String regDate, int hit) {
		this.title = title;
		this.body = body;
		this.articleNumber = articleNumber;
		this.regDate = regDate;
		this.hit = hit;
	}
	
	
	public void increaseHit() {
		hit++;
	}
}
