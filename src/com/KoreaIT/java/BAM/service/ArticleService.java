package com.KoreaIT.java.BAM.service;

import java.util.ArrayList;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dao.ArticleDao;
import com.KoreaIT.java.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public ArrayList<Article> getForPrintArticles(String searchKeyword) {
		
		ArrayList<Article> articles = articleDao.getArticles(searchKeyword);
		
		return articles;
	}

	public int setNewId() {
		
		int id = articleDao.setNewId();
		
		return id;
	}

	public void add(Article article) {
		
		articleDao.add(article);
	}

	public Article getArticleById(int id) {

		Article article = articleDao.getArticleById(id);
		
		return article;
	}

	public void remove(Article foundArticle) {
		
		articleDao.remove(foundArticle);
	}
}
