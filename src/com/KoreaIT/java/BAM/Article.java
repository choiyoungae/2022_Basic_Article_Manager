package com.KoreaIT.java.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {
	String title;
	String body;
	int articleNumber;
	LocalDateTime now = LocalDateTime.now();
	String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	
	
	public Article(String title, String body, int articleNumber, LocalDateTime now) {
		super();
		this.title = title;
		this.body = body;
		this.articleNumber = articleNumber;
		this.now = now;
	}
}
