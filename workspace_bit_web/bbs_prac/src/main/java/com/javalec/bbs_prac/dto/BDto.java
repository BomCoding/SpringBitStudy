package com.javalec.bbs_prac.dto;

import java.sql.Date;

public class BDto {
	private int bId;
	private String bUsername;
	private String bTitle;
	private String bContent;
	private Date bDate;
	private int bCount;
	private String bImage;
		
	public BDto() {}
	public BDto(int bId, String bUsername, String bTitle, String bContent, Date bDate, int bCount, String bImage) {
		this.bId = bId;
		this.bUsername = bUsername;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bCount = bCount;
		this.bImage = bImage;
	}
	

	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbUsername() {
		return bUsername;
	}
	public void setbUsername(String bUsername) {
		this.bUsername = bUsername;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public int getbCount() {
		return bCount;
	}
	public void setbCount(int bCount) {
		this.bCount = bCount;
	}
	public String getbImage() {
		return bImage;
	}
	public void setbImage(String bImage) {
		this.bImage = bImage;
	}
	
	@Override
	public String toString() {
		return "BDto [bId=" + bId + ", bUsername=" + bUsername + ", bTitle=" + bTitle + ", bContent=" + bContent
				+ ", bDate=" + bDate + ", bCount=" + bCount + " bImage" + bImage+"_]";
	}
	
}