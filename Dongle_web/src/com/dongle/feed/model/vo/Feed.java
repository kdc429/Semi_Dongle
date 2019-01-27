package com.dongle.feed.model.vo;

import java.sql.Date;

public class Feed {
	
	private int feedNo; // FEED_NO
	private int groupNo; //GROUP_NO
	private int memberNo; //MEMBER_NO
	private String feedContent; //FEED_CONTENT
	private Date feedWriteDate; //FEED_WRITE_DATE
	private String feedReportStatus;// FEED_REPORT_STATUS
	
	public Feed() {
		
	}

	public Feed(int feedNo, int groupNo, int memberNo, String feedContent, Date feedWriteDate, String feedReportStatus) {
		super();
		this.feedNo = feedNo;
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.feedContent = feedContent;
		this.feedWriteDate = feedWriteDate;
		this.feedReportStatus = feedReportStatus;
	}



	public int getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(int feedNo) {
		this.feedNo = feedNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getFeedContent() {
		return feedContent;
	}

	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}

	public Date getFeedWriteDate() {
		return feedWriteDate;
	}

	public void setFeedWriteDate(Date feedWriteDate) {
		this.feedWriteDate = feedWriteDate;
	}
	

	public String getFeedReportStatus() {
		return feedReportStatus;
	}

	public void setFeedReportStatus(String feedReportStatus) {
		this.feedReportStatus = feedReportStatus;
	}

	@Override
	public String toString() {
		return "Feed [feedNo=" + feedNo + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", feedContent="
				+ feedContent + ", feedWriteDate=" + feedWriteDate + ", feedReportStatus=" + feedReportStatus + "]";
	}

	
}
