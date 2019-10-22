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
	
	public boolean containsName(final List<AdaptationVO> list, final String new_id){
	    return list.stream().filter(o -> o.getAdaptation_id().equals(new_id)).findFirst().isPresent();
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void test(@RequestBody JSONObject request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(request.toString());
		String index = obj.at("/action/parameters/index/value").asText();
		logger.info(index);
	}
	
	@Inject 
	private AdaptationDAO dao;
	
	@Inject
	private AdaptationService service;
	
	private static Logger logger = LoggerFactory.getLogger(CrawlerTest.class);
	
	@Test
	public void JsonTest() throws Exception {
		logger.info(Arrays.toString(service.listAll().toArray()));
	}

}
