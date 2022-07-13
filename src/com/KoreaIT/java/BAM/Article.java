package com.KoreaIT.java.BAM;

public class Article {
	String title;
	String body;
	int articleNumber;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public Article(String title, String body, int articleNumber) {
		super();
		this.title = title;
		this.body = body;
		this.articleNumber = articleNumber;
	}
}
