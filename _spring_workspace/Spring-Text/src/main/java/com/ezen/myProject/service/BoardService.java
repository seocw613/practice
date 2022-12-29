package com.ezen.myProject.service;

import java.util.List;

import com.ezen.myProject.domain.BoardDTO;
import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.domain.UserVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int modify(BoardVO bvo, UserVO user);

	int remove(int bno);

	int getBoardCount(PagingVO pgvo);

	BoardDTO getDetailFile(int bno);

	int modify(BoardDTO bdto, UserVO user);

	int deleteFile(String uuid);

}
