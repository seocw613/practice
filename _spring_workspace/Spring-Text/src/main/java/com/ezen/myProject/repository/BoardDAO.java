package com.ezen.myProject.repository;

import java.util.List;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoardList(PagingVO pgvo);

	BoardVO selectDetail(int bno);

	void updateRead(BoardVO bvo);

	int updateBoard(BoardVO bvo);

	int updateBoardDel(int bno);

	int selectBoardCount(PagingVO pgvo);

	int selectLastBno();

}
