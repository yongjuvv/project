package com.ch.ch09.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch.ch09.model.Board;
import com.ch.ch09.service.BoardService;
import com.ch.ch09.service.PagingBean;
@Controller
public class BoardController {
	@Autowired
	private BoardService bs;
	@RequestMapping("list")
	public String list(Board board, String pageNum,Model model) {
		if (pageNum==null || pageNum.equals("")) pageNum="1";
		int total = bs.getTotal(board);
		int currentPage = Integer.parseInt(pageNum);
		int rowPerPage = 10;
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		PagingBean pb = new PagingBean(currentPage, rowPerPage,total);
		board.setStartRow(startRow);
		board.setEndRow(endRow);
		String[] tit = {"작성자","제목","내용","제목+내용"};
		List<Board> list = bs.list(board);
		model.addAttribute("list",list);
		model.addAttribute("tit", tit);
		model.addAttribute("pb", pb);
		model.addAttribute("pageNum", pageNum);
		return "list";
	}
	@RequestMapping("updateForm")
	public String updateForm(int num, String pageNum, Model model) {
		Board board = bs.select(num);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		return "updateForm";
	}
	@RequestMapping("update")
	public String update(Board board, String pageNum, Model model) {
		int result = bs.update(board);
		model.addAttribute("result",result);
		model.addAttribute("pageNum",pageNum);
		return "update";
	}
	@RequestMapping("deleteForm")
	public String deleteForm(int num, String pageNum, Model model) {
		Board board = bs.select(num);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		return "deleteForm";
	}
	@RequestMapping("delete")
	public String delete(int num, String pageNum, Model model) {
		int result = bs.delete(num);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "delete";
	}
	@RequestMapping("view")
	public String view(int num, String pageNum,Model model) {
		bs.updateReadCount(num); // 조회수 증가
		Board board = bs.select(num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("board", board);
		return "view";
	}
	@RequestMapping("insertBoard")
	public String insertBoard(HttpServletRequest request, Model model) {
		int cnt = 230;
		String ip = request.getLocalAddr();
		for (int i = 1; i <= cnt; i++) {
			Board board = new Board();
			board.setSubject("대박 "+i);
			board.setWriter("길동 "+i);
			board.setEmail("k"+i+"@k.com");
			board.setPassword("1");
			board.setContent("가을이 대박 "+i);
			board.setIp(ip);
			bs.insert(board);
		}
		model.addAttribute("cnt", cnt);
		return "insertBoard";
	}
	@RequestMapping("insertForm")
	public String insertForm(String nm, String pageNum, Model model) {
		int ref=0,re_level=0,re_step=0,num=0;
		if (nm!=null && !nm.contentEquals("")) { // 답변글
			num = Integer.parseInt(nm);
			Board board = bs.select(num);
			// 답변글이 아니면 ref와 num은 같지만 다변글은 원글의 ref를 사용
			ref = board.getRef();
			re_level = board.getRe_level();
			re_step = board.getRe_step();
		}
		model.addAttribute("ref", ref);
		model.addAttribute("re_level", re_level);
		model.addAttribute("re_step", re_step);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		return "insertForm";
	}
	@RequestMapping("insert")
	public String insert(Board board,String pageNum, Model model, 
			HttpServletRequest request) {
		String ip = request.getLocalAddr(); // 사용자 ip
		board.setIp(ip);
		int number = bs.maxNum();
		if (board.getNum() != 0) { // 답	변글
			// re_step값 정리 ref가 같고 re_step크면 +1
			bs.updateStep(board);
			// re_step, re_level은 읽은 글 +1
			board.setRe_level(board.getRe_level()+1);
			board.setRe_step(board.getRe_step()+1);
		} else board.setRef(number); // 답변글이 아니면 ref와 num은 같음
		board.setNum(number);
		int result = bs.insert(board);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "insert";
	}
}