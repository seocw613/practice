package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	public static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	public BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public List<BoardVO> getList() {
		log.info("list 2");
		return bdao.selectList();
	}

	@Override
	public int regi(BoardVO bvo) {
		log.info("regi 2");
		return bdao.insert(bvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("detail 2");
		return bdao.selectOne(bno);
	}

	@Override
	public BoardVO checkModify(BoardVO bvo) {
		log.info("check modify 2");
		return bdao.checkModify(bvo);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info("modify 2");
		return bdao.update(bvo);
	}

	@Override
	public void addView(BoardVO bvo) {
		log.info("updateView 1");
		bdao.updateView(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove 2");
		return bdao.delete(bno);
	}

	@Override
	public int getPageCnt() {
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getListPage(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}

	@Override
	public List<BoardVO> getMyList(String id) {
		log.info("myList 2");
		return bdao.selectMyList(id);
	}

}
