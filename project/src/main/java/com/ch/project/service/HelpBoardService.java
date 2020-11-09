package com.ch.project.service;

import java.util.List;

import com.ch.project.model.HelpBoard;

public interface HelpBoardService {

	int getTotal(HelpBoard board);

	List<HelpBoard> list(HelpBoard board);

}
