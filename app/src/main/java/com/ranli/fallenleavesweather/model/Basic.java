package com.ranli.fallenleavesweather.model;

import java.io.Serializable;

public class Basic implements Serializable {

	private static final long serialVersionUID = 1710371455686974063L;

	private String city;//��������
	  
	private String id;//����ID
	  
	private String loc;//���ݸ��µĵ���ʱ��
	  
	public String getCity() {
		 return city;
	}
	
	public void setCity(String city) {
		 this.city = city;
	}
	
	public String getId() {
		 return id;
	}
	
	public void setId(String id) {
		 this.id = id;
	}
	
	public String getLoc() {
		 return loc;
	}
	
	public void setLoc(String loc) {
		 this.loc = loc;
	}
	
}
