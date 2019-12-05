package com.kimreporter.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;

public interface UserInfoService {
	
	public void register(UserInfoVO vo) throws Exception;
	
	public UserInfoVO login(LoginDTO dto) throws Exception;

	public void logout(HttpSession session);
	
	public int selectListCount(String user_id) throws Exception;
	
	public List<AdaptationVO> selectMyAdaptations(String user_id) throws Exception;
	
	public void updateUser(UserInfoVO vo) throws Exception;
	
	public void updateUserStatus(String user_id) throws Exception;
	
	public UserInfoVO selectData(String user_id) throws Exception;

}
