package com.ch.project.model;

import java.sql.Date;

import lombok.Data;

@Data
public class productBoard {

	private int pbNum;
	private String pbTitle;
	private String pbContent;
	private String pbImage;
	private int pbReadCount;
	private int pbLike;
	private String pbIp;
	private Date date;
	
}
