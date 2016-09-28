package com.ranli.fallenleavesweather.model;

import java.io.Serializable;

public class Day implements Serializable {
	
	private static final long serialVersionUID = -8612056624270317499L;

	private String sr;//�ճ�ʱ��
	  
	private String ss;//����ʱ��
	
	private String date;//����
	  
	private String max;//����¶�(���϶�)
	  
	private String min;//����¶�(���϶�)
	  
	private String spd;//����(Kmph)
	  
	private String sc;//�����ȼ�
	  
	private String dir;//����(����)
	  
	private String code_d;//������������
	  
	private String txt_d;//������������
	  
	private String code_n;//ҹ����������
	  
	private String txt_n;//ҹ����������
	  
	private String pcpn;//������(mm)
	  
	private String pop;//��ˮ����
	  
	private String hum;//ʪ��(%)
	  
	private String pres;//��ѹ
	  
	private String vis;//�ܼ���(km)
	  
	public String getSr() {
		 return sr;
	}
	
	public void setSr(String sr) {
		 this.sr = sr;
	}
	
	public String getSs() {
		 return ss;
	}
	
	public void setSs(String ss) {
		 this.ss = ss;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMax() {
		 return max;
	}
	
	public void setMax(String max) {
		 this.max = max;
	}
	
	public String getMin() {
		 return min;
	}
	
	public void setMin(String min) {
		 this.min = min;
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
	
	public String getCode_d() {
		 return code_d;
	}
	
	public void setCode_d(String code_d) {
		 this.code_d = code_d;
	}
	
	public String getTxt_d() {
		 return txt_d;
	}
	
	public void setTxt_d(String txt_d) {
		 this.txt_d = txt_d;
	}
	
	public String getCode_n() {
		 return code_n;
	}
	
	public void setCode_n(String code_n) {
		 this.code_n = code_n;
	}
	
	public String getTxt_n() {
		 return txt_n;
	}
	
	public void setTxt_n(String txt_n) {
		 this.txt_n = txt_n;
	}
	
	public String getPcpn() {
		 return pcpn;
	}
	
	public void setPcpn(String pcpn) {
		 this.pcpn = pcpn;
	}
	
	public String getPop() {
		 return pop;
	}
	
	public void setPop(String pop) {
		 this.pop = pop;
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
