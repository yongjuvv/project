package com.ch.ch09.service;
import lombok.Data;
@Data
public class PagingBean {
	private int currentPage;	private int rowPerPage;
	private int total;			private int pagePerBlock = 10;
	private int totalPage;		private int startPage;
	private int endPage;
	public PagingBean(int currentPage, int rowPerPage, int total) {
		this.currentPage=currentPage; this.rowPerPage=rowPerPage;
		this.total=total;
		// Math.ceil 현재 수보다 크거나 같은 정수 중에서 가장 작은 정수
		totalPage = (int)Math.ceil((double)total/rowPerPage);
		startPage = currentPage - (currentPage - 1) % pagePerBlock;
		endPage = startPage + pagePerBlock - 1;
		if (endPage > totalPage) endPage = totalPage;
	}
}