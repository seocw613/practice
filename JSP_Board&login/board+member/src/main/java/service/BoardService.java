package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	List<BoardVO> getList();

	int regi(BoardVO bvo);

	BoardVO getDetail(int bno);

	BoardVO checkModify(BoardVO bvo);

	int modify(BoardVO bvo);

	void addView(BoardVO bvo);

	int remove(int bno);

	int getPageCnt();

	List<BoardVO> getListPage(PagingVO pgvo);

	List<BoardVO> getMyList(String id);

}
