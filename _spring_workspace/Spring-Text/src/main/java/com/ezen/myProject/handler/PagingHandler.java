package com.ezen.myProject.handler;

import com.ezen.myProject.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingHandler {
	private int startPage;  // 현재 페이지에서 나타낼 첫 페이지네이션 번호
	private int endPage;  // 현재 페이지에서 나타낼 끝 페이지네이션 번호
	private int pagenationQty = 10;  // 현재 페이지에서 나타낼 페이지네이션 개수
	private boolean prev, next;  // 이전, 다음 페이지네이션 번호
	
	private int totalCount;  // 총 게시글 수
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int)(Math.ceil(pgvo.getPageNo()/(double)pagenationQty)*pagenationQty);
		this.startPage = endPage - (pagenationQty - 1);
		int realEndPage = (int)Math.ceil((double)totalCount/pgvo.getQty());
		if(endPage>realEndPage) endPage = realEndPage;
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
