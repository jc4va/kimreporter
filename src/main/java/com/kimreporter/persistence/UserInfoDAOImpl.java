package com.kimreporter.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

}
