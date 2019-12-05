package com.kimreporter.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;
import com.kimreporter.persistence.UserInfoDAO;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Inject
	private UserInfoDAO dao;
	
	@Override
	public void register(UserInfoVO vo) throws Exception {
		dao.register(vo);
	}

	@Override
	public UserInfoVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public int selectListCount(String user_id) throws Exception {
		return dao.selectListCount(user_id);
	}

	@Override
	public List<AdaptationVO> selectMyAdaptations(String user_id) throws Exception {
		return dao.selectMyAdaptations(user_id);
	}

	@Override
	public void updateUser(UserInfoVO vo) throws Exception {
		dao.updateUser(vo);
	}
	
	@Override
	public void updateUserStatus(String user_id) throws Exception {
		dao.updateUserStatus(user_id);
	}

	@Override
	public UserInfoVO selectData(String user_id) throws Exception {
		return dao.selectData(user_id);
	}

}
