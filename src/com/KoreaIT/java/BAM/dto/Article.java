package com.KoreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public String writer;
	
	public Article(String title, String body, int id, String regDate) {
		this(title, body, id, regDate, 0, "°ü¸®ÀÚ");
	}
	
	public Article(String title, String body, int id, String regDate, String writer) {
		this(title, body, id, regDate, 0, writer);
	}
	
	public Article(String title, String body, int id, String regDate, int hit, String writer) {
		this.title = title;
		this.body = body;
		this.id = id;
		this.regDate = regDate;
		this.hit = hit;
		this.writer = writer;
	}
	
	
	public void increaseHit() {
		hit++;
	}
}
