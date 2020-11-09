package com.ch.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.project.dao.HelpBoardDao;
import com.ch.project.model.HelpBoard;
@Service
public class HelpBoardServiceImpl implements HelpBoardService{
	@Autowired
	private HelpBoardDao hd;
	
	public int getTotal(HelpBoard board) {
		return hd.getTotal(board);
	}
	public List<HelpBoard> list(HelpBoard board) {
		return hd.list(board);
	}

}
