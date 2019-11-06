package com.kimreporter.service;

import java.util.List;

import com.kimreporter.domain.AdaptationVO;

public interface AdaptationService {
	
	public void regist(AdaptationVO adaptation) throws Exception;
	public AdaptationVO read(String adaptation_id) throws Exception;
	public void modify(AdaptationVO adaptation) throws Exception;
	public void delete() throws Exception;
	public List<AdaptationVO> listAll() throws Exception;
	
}
