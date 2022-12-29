package service;

import domain.MemberVO;

public interface MemberService {

	int signup(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int modify(MemberVO memberVO);

	int withdraw(String id);

	int lastLog(String id);

}
