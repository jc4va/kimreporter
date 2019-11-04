package com.kimreporter.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.persistence.AdaptationDAO;

@Service
public class AdaptationServiceImpl implements AdaptationService{
	
	@Inject 
	private AdaptationDAO dao;

	@Override
	public void regist(AdaptationVO adaptation) throws Exception {
		String url = "https://media.daum.net/ranking/popular/";
		String news_url = "https://news.v.daum.net/v/";
		ArrayList<ArrayList<String> > summarized_news = new ArrayList<ArrayList<String> >();
		List<AdaptationVO > all_list = dao.listAll();
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.getElementsByClass("tit_thumb").select("a[href]");
			Elements rankings = doc.select("ul.list_news2").select("span.screen_out");
			// 링크 id (맨 뒤에 17숫자)를 찾아서 저장  
			for (int i=0; i < 50; i++) {
				ArrayList<String> news_links = new ArrayList<String>();
				String link = links.get(i).attr("abs:href").toString();
				String rank = rankings.get(i).text();
				String parsed_link = link.substring(link.lastIndexOf("/") + 1);
				Document opened_link = Jsoup.connect(news_url+ parsed_link).get();
				Elements summary = opened_link.getElementsByClass("layer_util layer_summary").select("p");
				if (summary.text().isEmpty()) {
					
				}
				else {
					Elements news_title = opened_link.getElementsByClass("tit_view").select("h3");
					news_links.add(parsed_link);
					news_links.add(rank);
					news_links.add(news_title.text());
					news_links.add(summary.text());
					summarized_news.add(news_links);
				}
			}
			
			// create(AdaptationVO vo, String title, String content, String id, int ranking)
			// 저장 해 놓은 결과를 데이터베이스에 저장 
			
			for (ArrayList<String> arr:summarized_news) {
				
				AdaptationVO vo = dao.read(arr.get(0));
				
				if (vo != null) {
					vo.setRanking(Integer.valueOf(arr.get(1)));
					dao.update(vo);
				} else {
					dao.create(adaptation, arr.get(2), arr.get(3), arr.get(0), Integer.valueOf(arr.get(1)));
				}
			}
			/*
			
			for (ArrayList<String> arr:summarized_news) {
				
				AdaptationVO vo = dao.read(arr.get(0)); // 크롤링한 기사 하나하나의 id로 이미 db에 저장되어있는 adaptation을 불러온다 
				
				if (vo != null  && Integer.valueOf(arr.get(1)) != vo.getRanking()) {
					vo.setRanking(Integer.valueOf(arr.get(1)));
				}
				
				else if (vo == null) {
					vo.setRanking(-1);
				}
				
				else {
					dao.create(adaptation, arr.get(2), arr.get(3), arr.get(0), Integer.valueOf(arr.get(1)));
				}
				----
				
				if (vo != null) {
					vo.setRanking(Integer.valueOf(arr.get(1)));
					dao.update(vo);
				} else {
					dao.create(adaptation, arr.get(2), arr.get(3), arr.get(0), Integer.valueOf(arr.get(1)));
				}
				
				*/
			// loop through summarized_news and today_list
			// if vo in today_list not in summarized_news -> set ranking to minus
			// if vo in today_list -> update ranking 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public AdaptationVO read(String adaptation_id) throws Exception {
		return dao.read(adaptation_id);
	}

	@Override
	public void modify(AdaptationVO adaptation) throws Exception {
		dao.update(adaptation);
		
	}

	@Override
	public void delete(String adaptation_id) throws Exception {
		dao.delete(adaptation_id);
		
	}

	@Override
	public List<AdaptationVO> listAll() throws Exception {
		return dao.listAll();
	}

}
