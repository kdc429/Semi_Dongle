package com.dongle.feed.model.vo;

import java.sql.Date;

public class Feed {
	
	private int feedNo; // FEED_NO
	private int groupNo; //GROUP_NO
	private int memberNo; //MEMBER_NO
	private String feedContent; //FEED_CONTENT
	private Date feedWriteDate; //FEED_WRITE_DATE
	
	public Feed() {
		
	}

	public Feed(int feedNo, int groupNo, int memberNo, String feedContent, Date feedWriteDate) {
		super();
		this.feedNo = feedNo;
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.feedContent = feedContent;
		this.feedWriteDate = feedWriteDate;
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

	@Override
	public String toString() {
		return "Feed [feedNo=" + feedNo + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", feedContent="
				+ feedContent + ", feedWriteDate=" + feedWriteDate + "]";
	}
	
	
}
