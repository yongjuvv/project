package com.ch.project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.project.model.HelpBoard;

@Repository
public class HelpBoardDaoImpl implements HelpBoardDao{
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int getTotal(HelpBoard board) {
		return sst.selectOne("boardns.getTotal", board);
	}

	@Override
	public List<HelpBoard> list(HelpBoard board) {
		return sst.selectList("boardns.list", board);
	}
	
	
}
