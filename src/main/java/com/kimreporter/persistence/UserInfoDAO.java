package com.kimreporter.persistence;

import java.util.List;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;

public interface UserInfoDAO {
	
	public void register(UserInfoVO vo) throws Exception;
	
	public UserInfoVO login(LoginDTO dto) throws Exception;
	
	public int selectListCount(String user_id) throws Exception;
	
	public List<AdaptationVO> selectMyAdaptations(String user_id) throws Exception;
	
	public void updateUser(UserInfoVO vo) throws Exception;

	public UserInfoVO selectData(String user_id);

	public void updateUserStatus(String user_id);

}
