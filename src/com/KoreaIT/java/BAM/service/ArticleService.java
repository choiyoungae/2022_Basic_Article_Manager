package com.KoreaIT.java.BAM.service;

import java.util.ArrayList;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Article;

public class ArticleService {
	
	public ArrayList<Article> getForPrintArticles(String searchKeyword) {
		ArrayList<Article> forPrintArticles = Container.articleDao.getForPrintArticles(searchKeyword);
		return forPrintArticles;
	}
}
