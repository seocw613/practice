package service;

import java.util.List;

import domain.CommentVO;

public interface CommentSerivce {

	int post(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int remove(int cno);

	int modify(CommentVO cvo);

}
