package com.ezen.myProject.repository;

import java.util.List;

import com.ezen.myProject.domain.CommentVO;

public interface CommentDAO {

	int insertComment(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int modify(CommentVO cvo);

	int delete(CommentVO cvo);

}
