package com.kimreporter.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kimreporter.domain.AdaptationVO;

@Repository
public class AdaptationDAOImpl implements AdaptationDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.kimreporter.mapper.AdaptationMapper";


	@Override
	public AdaptationVO read(String adaptation_id) throws Exception {
		return session.selectOne(namespace+".read", adaptation_id);
	}

	@Override
	public void update(AdaptationVO vo) throws Exception {
		session.update(namespace+ ".update", vo);
		
	}

	@Override
	public void delete(String adaptation_id) throws Exception {
		session.delete(namespace+ ".delete", adaptation_id);
		
	}

	@Override
	public List<AdaptationVO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public void create(AdaptationVO vo, String title, String content, String id, int ranking) throws Exception {
		vo.setAdaptation_id(id);
		vo.setArticle_title(title);
		vo.setArticle_content(content);
		vo.setRanking(ranking);
		session.insert(namespace+".create", vo);
	}

	@Override
	public void updateRanking(AdaptationVO vo, int ranking) throws Exception {
		vo.setRanking(ranking);
		session.update(namespace+".updateRanking", vo);
		
	}

}
