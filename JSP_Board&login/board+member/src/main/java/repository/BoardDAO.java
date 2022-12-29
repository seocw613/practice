package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	List<BoardVO> selectList();

	int insert(BoardVO bvo);

	BoardVO selectOne(int bno);

	BoardVO checkModify(BoardVO bvo);

	int update(BoardVO bvo);

	void updateView(BoardVO bvo);

	int delete(int bno);

	int selectCount();

	List<BoardVO> selectList(PagingVO pgvo);

	List<BoardVO> selectMyList(String id);

}
