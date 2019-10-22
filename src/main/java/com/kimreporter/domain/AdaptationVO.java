package com.kimreporter.domain;

public class AdaptationVO {
	
	private String adaptation_id;
	private String user_id;
	private String adaptation_content;
	private String article_title;
	private String article_content;
	private int ranking;
	private String inserted_time;
	
	public AdaptationVO() {
	}
	
	public AdaptationVO(String adaptation_id, String user_id, String adaptation_content, String article_title,
			String article_content, int ranking) {
		this.adaptation_id = adaptation_id;
		this.user_id = user_id;
		this.adaptation_content = adaptation_content;
		this.article_title = article_title;
		this.article_content = article_content;
		this.ranking = ranking;
	}
	
	public String getAdaptation_id() {
		return adaptation_id;
	}
	public void setAdaptation_id(String adaptation_id) {
		this.adaptation_id = adaptation_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAdaptation_content() {
		return adaptation_content;
	}
	public void setAdaptation_content(String adaptation_content) {
		this.adaptation_content = adaptation_content;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public String getInserted_time() {
		return inserted_time;
	}

	public void setInserted_time(String inserted_time) {
		this.inserted_time = inserted_time;
	}

	@Override
	public String toString() {
		return "AdaptationVO [adaptation_id=" + adaptation_id + ", user_id=" + user_id + ", adaptation_content="
				+ adaptation_content + ", article_title=" + article_title + ", article_content=" + article_content
				+ ", ranking=" + ranking + ", inserted_time=" + inserted_time + "]";
	}
	
	
}
