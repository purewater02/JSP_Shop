package com.jsp.model;

public class Paging {
	private int page = 1;	//현재 페이지
	private int totalCount; //전체 ROW 개수
	private int beginPage;	//블록의 시작 페이지
	private int endPage;	//블록의 끝 페이지
	private int displayRow;	//한 페이지에 출력할 행의 갯수
	private int displayPage;	//한 블록에 출력할 페이지의 갯수
	private int startNum;
	private int endNum;
	boolean prev;	
	boolean next;
	
	public int getStartNum() {
		this.startNum = (page-1)*displayRow+1;
		return startNum;
	}	
	public int getEndNum() {
		this.endNum = page*displayRow;
		return endNum;
	}	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paging(); //setTotalCount를 호출해야만 paging이 가능하기 때문에 paging() 메서드를 setTotalCount안쪽에 정의.
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
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
	
	private void paging() {
		endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
		beginPage = endPage - (displayPage-1);
		int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
		
		if(totalPage < endPage) {
			endPage = totalPage;
			next = false;
		}else {
			next = true;
		}
		
		prev = (beginPage==1) ? false : true;		
	}
}
