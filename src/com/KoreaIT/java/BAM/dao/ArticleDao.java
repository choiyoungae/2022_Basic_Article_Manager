package com.KoreaIT.java.BAM.dao;

import java.util.ArrayList;

import com.KoreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao {
	public ArrayList<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	
}
