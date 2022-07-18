package com.KoreaIT.java.BAM.dto;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Article {
	public String title;
	public String body;
	public int articleNumber;
//	LocalDateTime now = LocalDateTime.now();
//	String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
