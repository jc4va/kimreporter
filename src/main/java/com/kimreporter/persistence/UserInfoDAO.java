package com.kimreporter.persistence;

import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;

public interface UserInfoDAO {
	
	public void register(UserInfoVO vo) throws Exception;
	public UserInfoVO login(LoginDTO dto) throws Exception;

}
