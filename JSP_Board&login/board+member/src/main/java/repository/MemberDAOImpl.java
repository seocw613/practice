package repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemberMapper.";
	private int isOk;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("signup 3");
		isOk = sql.insert(NS+"signup",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info("login 3");
		log.info(NS);
		return sql.selectOne(NS+"login",mvo);
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("modify 3");
		isOk = sql.update(NS+"modify",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info("withdraw 3");
		isOk = sql.delete(NS+"withdraw",id);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int lastLog(String id) {
		log.info("last login check 2");
		isOk = sql.update(NS+"lastLogin", id);
		if(isOk>0) sql.commit();
		return isOk;
	}
	
}
