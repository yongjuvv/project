package com.ch.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.project.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	
	private LoginDao ld;

	
}
