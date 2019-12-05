package com.kimreporter.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO{
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.kimreporter.mapper.UserInfoMapper";

	@Override
	public void register(UserInfoVO vo) throws Exception {
		sql.insert(namespace + ".register", vo);
		
	}
	
	@Override
	public UserInfoVO login(LoginDTO dto) throws Exception {
		return sql.selectOne(namespace + ".login", dto);
	}

	@Override
	public int selectListCount(String user_id) throws Exception {
		return sql.selectOne(namespace + ".selectListCount", user_id);
	}

	@Override
	public List<AdaptationVO> selectMyAdaptations(String user_id) throws Exception {
		return sql.selectList(namespace + ".selectMyAdaptations", user_id);
	}

	@Override
	public void updateUser(UserInfoVO vo) throws Exception {
		sql.update(namespace + ".updateUser", vo);
	}

	@Override
	public UserInfoVO selectData(String user_id) {
		return sql.selectOne(namespace + ".selectData", user_id);
	}

	@Override
	public void updateUserStatus(String user_id) {
		sql.update(namespace + ".updateUserStatus", user_id);
	}

	@Override
	public int selectListCountEmail(String user_email) {
		return sql.selectOne(namespace + ".selectListCountEmail", user_email);
	}
	
	@Override
	public int selectListCountID(String user_id) {
		return sql.selectOne(namespace + ".selectListCountID", user_id);
	}

}
