package com.kimreporter.crawlertest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domaintest.AdaptationDAOTest;
import com.kimreporter.persistence.AdaptationDAO;
import com.kimreporter.service.AdaptationService;

public class CrawlerTest {
	
	@Inject 
	private AdaptationDAO dao;
	
	@Inject
	private AdaptationService service;
	
	private static Logger logger = LoggerFactory.getLogger(CrawlerTest.class);
	
	@Test
	public void CrawlTest() throws Exception {
		String url = "https://media.daum.net/ranking/popular/";
		String news_url = "https://news.v.daum.net/v/";
		ArrayList<ArrayList<String> > summarized_news = new ArrayList<ArrayList<String> >();
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.getElementsByClass("tit_thumb").select("a[href]");
		Elements rankings = doc.select("ul.list_news2").select("span.screen_out");
		// 링크 id (맨 뒤에 17숫자)를 찾아서 저장  
		int article_counter = 0;
		int i = 0;
		while(article_counter < 20) {
			ArrayList<String> news_links = new ArrayList<String>();
			String link = links.get(i).attr("abs:href").toString();
			String rank = rankings.get(i).text();
			String parsed_link = link.substring(link.lastIndexOf("/") + 1);
			Document opened_link = Jsoup.connect(news_url+ parsed_link).get();
			Elements summary = opened_link.getElementsByClass("layer_util layer_summary").select("p");
			if (summary.text().isEmpty()) {
					
			}
			else {
				article_counter++;
				Elements news_title = opened_link.getElementsByClass("tit_view").select("h3");
				news_links.add(parsed_link);
				news_links.add(rank);
				news_links.add(news_title.text());
				news_links.add(summary.text());
				summarized_news.add(news_links);
			}
			i++;
		}
		logger.info(Arrays.toString(summarized_news.toArray()));
	}

}
