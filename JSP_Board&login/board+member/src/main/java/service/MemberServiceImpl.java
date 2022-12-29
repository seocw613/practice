package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int signup(MemberVO mvo) {
		log.info("signup 2");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("signin 2");
		return mdao.selectOne(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("modify 2");
		return mdao.update(mvo);
	}

	@Override
	public int withdraw(String id) {
		log.info("withdraw 2");
		return mdao.delete(id);
	}

	@Override
	public int lastLog(String id) {
		log.info("last login check 1");
		return mdao.lastLog(id);
	}

}
