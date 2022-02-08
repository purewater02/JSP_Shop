package com.jsp.model;

public class ReviewDTO {
	private int bNo;
	private String bTitle;
	private String bWriter;
	private String bCont;
	private String bDate;
	private String bPwd;
	private String pcode;
	private int bstar;
	private String pName;
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getBstar() {
		return bstar;
	}
	public void setBstar(int bstar) {
		this.bstar = bstar;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbCont() {
		return bCont;
	}
	public void setbCont(String bCont) {
		this.bCont = bCont;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public String getbPwd() {
		return bPwd;
	}
	public void setbPwd(String bPwd) {
		this.bPwd = bPwd;
	}
}
