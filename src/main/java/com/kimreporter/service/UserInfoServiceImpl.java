package com.kimreporter.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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

}
