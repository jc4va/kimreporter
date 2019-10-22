package com.kimreporter.service;

import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;

public interface UserInfoService {
	
	public void register(UserInfoVO vo) throws Exception;
	
	public UserInfoVO login(LoginDTO dto) throws Exception;

}
