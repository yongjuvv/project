package com.ch.project.model;

import java.sql.Date;

import lombok.Data;

@Data
public class product {

	private int pNum;
	private String PCompany;
	private String pName;
	private int pPrice;
	private int pQuantity;
	private Date pRegDate;
	private String pColor;
	
	
	
}
