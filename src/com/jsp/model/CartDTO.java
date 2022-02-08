package com.jsp.model;

public class CartDTO {
	private int cartNo;
	private String sessionId;
	private int pNo;
	private String pName;
	private int  pAmount;
	private int pPrice;
	private int totPrice;
	private String pSize;
	private String pColor;
	private String pImage;
	private int pMileage;
	private String pSizeList;
	private String pColorList;	
	
	public String getpColorList() {
		return pColorList;
	}
	public void setpColorList(String pColorList) {
		this.pColorList = pColorList;
	}
	public String getpSizeList() {
		return pSizeList;
	}
	public void setpSizeList(String pSizeList) {
		this.pSizeList = pSizeList;
	}
	public int getpMileage() {
		return pMileage;
	}
	public void setpMileage(int pMileage) {
		this.pMileage = pMileage;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpAmount() {
		return pAmount;
	}
	public void setpAmount(int pAmount) {
		this.pAmount = pAmount;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getTotPrice() {
		return totPrice;
	}
	
	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}
	
	public String getpSize() {
		return pSize;
	}
	public void setpSize(String pSize) {
		this.pSize = pSize;
	}
	public String getpColor() {
		return pColor;
	}
	public void setpColor(String pColor) {
		this.pColor = pColor;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	
	
}
