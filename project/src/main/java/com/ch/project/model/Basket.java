package com.ch.project.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Basket {

	private int baNum;
	private int baQuantity;
	private int baTotalPrice;
	private Date baDate;
	
}
