package com.ezen.myProject.repository;

import com.ezen.myProject.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String id);  // 아이디 유효성 검사를 위한 메서드

	int insertUser(UserVO user);  // 회원가입 메서드

}
