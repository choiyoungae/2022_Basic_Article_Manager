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
	
	public ArrayList<Article> getArticles(String searchKeyword) {
		
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
	
	private int getArticleIndexById(int id) {
		
		int i=0;
		
		for(Article article : articles) {
			
			if(article.id == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public Article getArticleById(int id) {
		
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			return articles.get(index);
		}
		
		return null;
	}

	public void remove(Article foundArticle) {
		
		articles.remove(foundArticle);
	}
}
