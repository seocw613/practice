package com.ezen.myProject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.CommentVO;
import com.ezen.myProject.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO cdao;

	@Override
	public int register(CommentVO cvo) {
		return cdao.insertComment(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("comment list csv");
		return cdao.getList(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("comment modify csv");
		return cdao.modify(cvo);
	}

	@Override
	public int delete(CommentVO cvo) {
		log.info("comment delete csv");
		return cdao.delete(cvo);
	}
}
