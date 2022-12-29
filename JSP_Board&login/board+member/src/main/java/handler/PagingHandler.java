package handler;

import domain.PagingVO;

public class PagingHandler {
	private int startPage;  // 현재 화면에서 보여줄 시작 페이지 번호
	private int endPage;  // 현재 화면에서 보여줄 마지막 페이지 번호
	private boolean prev, next;  // 이전, 다음 페이지 존재 여부
	private int pageQty = 10;
	
	private int totalCount;  // 총 게시글 수
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(pageQty*1.0))*pageQty;
		this.startPage = this.endPage - (pageQty - 1);
		int realEndPage = (int)(Math.ceil((totalCount*1.0)/pageQty));  // 실제 끝 번호
		if(realEndPage < this.endPage) this.endPage = realEndPage;
		// 우항 자체가 boolean 값을 가진다.
		this.prev = this.startPage > 1;  // startPage가 1보다 크면 prev 버튼이 생긴다.
		this.next = this.endPage < realEndPage;  // endPage가 realEndPage보다 작으면 next 버튼이 생긴다.
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageQty() {
		return pageQty;
	}

	public void setPageQty(int pageQty) {
		this.pageQty = pageQty;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
	
	
}
