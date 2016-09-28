package com.ranli.fallenleavesweather.model;

import java.io.Serializable;

public class Suggestion implements Serializable {
	
	private static final long serialVersionUID = 6471189548340656435L;

	private String comf;//����ָ��
	
	private String drsg;//����ָ��
	  
	private String uv;//������ָ��
	  
	private String cw;//ϴ��ָ��
	  
	private String trav;//����ָ��
	  
	private String flu;//��ðָ��
	  
	private String sport;//�˶�ָ��
	  
	public String getComf() {
		return comf;
	}

	public void setComf(String comf) {
		this.comf = comf;
	}

	public String getDrsg() {
		 return drsg;
	}
	
	public void setDrsg(String drsg) {
		 this.drsg = drsg;
	}
	
	public String getUv() {
		 return uv;
	}
	
	public void setUv(String uv) {
		 this.uv = uv;
	}
	
	public String getCw() {
		 return cw;
	}
	
	public void setCw(String cw) {
		 this.cw = cw;
	}
	
	public String getTrav() {
		 return trav;
	}
	
	public void setTrav(String trav) {
		 this.trav = trav;
	}
	
	public String getFlu() {
		 return flu;
	}
	
	public void setFlu(String flu) {
		 this.flu = flu;
	}
	
	public String getSport() {
		 return sport;
	}
	
	public void setSport(String sport) {
		 this.sport = sport;
	}
	
}
