package com.ranli.fallenleavesweather.model;

import java.io.Serializable;

public class Now implements Serializable {
	
	private static final long serialVersionUID = -6264193161807162342L;

	private String fl;//����¶�
	  
	private String spd;//����(Kmph)
	  
	private String sc;//�����ȼ�
	  
	private String dir;//����(����)
	  
	private String code;//��������
	  
	private String txt;//��������
	  
	private String pcpn;//������(mm)
	  
	private String hum;//ʪ��(%)
	  
	private String pres;//��ѹ
	  
	private String vis;//�ܼ���(km)
	
	private String tmp;//��ǰ����(���϶�)
	  
	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public String getFl() {
		 return fl;
	}
	
	public void setFl(String fl) {
		 this.fl = fl;
	}
	
	public String getSpd() {
		 return spd;
	}
	
	public void setSpd(String spd) {
		 this.spd = spd;
	}
	
	public String getSc() {
		 return sc;
	}
	
	public void setSc(String sc) {
		 this.sc = sc;
	}
	
	public String getDir() {
		 return dir;
	}
	
	public void setDir(String dir) {
		 this.dir = dir;
	}
	
	public String getCode() {
		 return code;
	}
	
	public void setCode(String code) {
		 this.code = code;
	}
	
	public String getTxt() {
		 return txt;
	}
	
	public void setTxt(String txt) {
		 this.txt = txt;
	}
	
	public String getPcpn() {
		 return pcpn;
	}
	
	public void setPcpn(String pcpn) {
		 this.pcpn = pcpn;
	}
	
	public String getHum() {
		 return hum;
	}
	
	public void setHum(String hum) {
		 this.hum = hum;
	}
	
	public String getPres() {
		 return pres;
	}
	
	public void setPres(String pres) {
		 this.pres = pres;
	}
	
	public String getVis() {
		 return vis;
	}
	
	public void setVis(String vis) {
		 this.vis = vis;
	}
	
}
