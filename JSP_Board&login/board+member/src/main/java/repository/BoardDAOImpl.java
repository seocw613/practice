package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	public SqlSession sql;
	public static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	public final String NS = "BoardMapper.";
	public int isOk;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public List<BoardVO> selectList() {
		log.info("list 3");
		return sql.selectList(NS+"list");
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("regi 3");
		isOk = sql.insert(NS+"regi",bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("detail 3");
		return sql.selectOne(NS+"detail",bno);
	}

	@Override
	public BoardVO checkModify(BoardVO bvo) {
		log.info("check modify 3");
		return sql.selectOne(NS+"checkModify", bvo);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("modify 3");
		isOk = sql.update(NS+"modify",bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public void updateView(BoardVO bvo) {
		log.info("updateView 2");
		isOk = sql.update(NS+"updateView", bvo);
		if(isOk>0) sql.commit();
	}

	@Override
	public int delete(int bno) {
		log.info("remove 3");
		isOk = sql.delete(NS+"delete",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int selectCount() {
		return sql.selectOne(NS+"cnt");
	}

	@Override
	public List<BoardVO> selectList(PagingVO pgvo) {
		return sql.selectList(NS+"pagingList",pgvo);
	}

	@Override
	public List<BoardVO> selectMyList(String id) {
		return sql.selectList(NS+"myList",id);
	}

}
