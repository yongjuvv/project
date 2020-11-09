package com.ch.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch.project.model.HelpBoard;
import com.ch.project.service.HelpBoardService;
import com.ch.project.service.LoginService;
import com.ch.project.service.PagingBean;

@Controller
public class mainController {

   @Autowired   
   private LoginService ls;
   
   @RequestMapping("main")
   private String main() {
      return "main";
   }
   @Autowired
   private HelpBoardService hs;
   
   @RequestMapping("helplist")
   public String helplist() {
	   return "helplist";
   }
   @RequestMapping("list")
   public String list(HelpBoard board, String pageNum, Model model) {
	   if(pageNum == null || pageNum.equals("")) pageNum="1";
	   int total = hs.getTotal(board);
	   int currentPage = Integer.parseInt(pageNum);
	   int rowPerPage = 10;
	   int startRow = (currentPage - 1) * rowPerPage + 1;
	   int endRow = startRow + rowPerPage -1;
	   PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
	   board.setStartRow(startRow);
	   board.setEndRow(endRow);
	   String[] tit = {"작성자", "제목", "내용", "제목 + 내용"};
	   List<HelpBoard> list = hs.list(board);
	   model.addAttribute("list", list);
	   model.addAttribute("tit", tit);
	   model.addAttribute("pb", pb);
	   model.addAttribute("pageNum", pageNum);
	   return "list";
   }
}