package com.KoreaIT.java.BAM.dao;

import java.util.ArrayList;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao {
	private ArrayList<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	
	public ArrayList<Article> getArticles() {
		return articles;
	}
	
	public ArrayList<Article> getForPrintArticles(String searchKeyword) {
		
		ArrayList<Article> articles = Container.articleDao.getArticles();
		
		if(searchKeyword != null && searchKeyword.length() != 0) {
			ArrayList<Article> forPrintArticles = new ArrayList<>();
			
			if(searchKeyword.length() > 0) {
				
				for(Article article : articles) {
					if(article.title.contains(searchKeyword)) {
						forPrintArticles.add(article);
					}
				}
			}
			return forPrintArticles;
		}
		
		return articles;
	}
	
}
