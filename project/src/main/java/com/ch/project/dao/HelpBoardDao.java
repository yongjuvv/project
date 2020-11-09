package com.ch.project.dao;

import java.util.List;

import com.ch.project.model.HelpBoard;

public interface HelpBoardDao {

	int getTotal(HelpBoard board);

	List<HelpBoard> list(HelpBoard board);

}
