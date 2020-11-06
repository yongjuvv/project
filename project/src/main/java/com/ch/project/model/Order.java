package com.ch.project.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Order {

	private int orNumber;
	private Date orDate;
	private int orTotalCost;
	private String orDeliveryState;
	
	
}
