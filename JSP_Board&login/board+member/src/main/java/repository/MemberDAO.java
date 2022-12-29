package repository;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO selectOne(MemberVO mvo);

	int update(MemberVO mvo);

	int delete(String id);

	int lastLog(String id);

}
