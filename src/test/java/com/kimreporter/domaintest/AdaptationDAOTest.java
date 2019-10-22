package com.kimreporter.domaintest;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.persistence.AdaptationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdaptationDAOTest {
	
	@Inject
	private AdaptationDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(AdaptationDAOTest.class);
	
	@Test
	public void testCreate() throws Exception {
		AdaptationVO adaptation = new AdaptationVO();
		adaptation.setAdaptation_id("2019239182");
		adaptation.setArticle_title("테스트 타이틀 ");
		adaptation.setArticle_content("테스트 콘텐트 ");
		dao.update(adaptation);
		logger.info(adaptation.toString());
	}
}
