package com.ranli.fallenleavesweather.model;

import java.io.Serializable;

public class Daily implements Serializable{
	
	private static final int length = 7;
	
	private static final long serialVersionUID = -1114558877762141995L;

	private Day[] sevenDays;
	
	public Daily() {
		// TODO Auto-generated constructor stub
		sevenDays = new Day[length];
	}

	public Day[] getSevenDays() {
		return sevenDays;
	}

	public void setSevenDays(Day[] sevenDays) {
		this.sevenDays = sevenDays;
	}
	
}
