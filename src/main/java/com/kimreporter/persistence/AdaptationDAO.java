package com.kimreporter.persistence;

import java.util.List;

import com.kimreporter.domain.AdaptationVO;

public interface AdaptationDAO {
	
	public void create(AdaptationVO vo, String title, String content, String id, int ranking) throws Exception;
	
	public AdaptationVO read(String adaptation_id) throws Exception;
	
	public void update(AdaptationVO vo) throws Exception;
	
	public void updateRanking(AdaptationVO vo, int ranking) throws Exception;
	
	public void delete(String adaptation_id) throws Exception;
	
	public List<AdaptationVO> listAll() throws Exception;


}
