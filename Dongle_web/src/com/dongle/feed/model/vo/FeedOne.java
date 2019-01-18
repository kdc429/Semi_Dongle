package com.dongle.feed.model.vo;

import java.sql.Date;

public class FeedOne {

	private int feedNo; // FEED_NO
	private int groupNo; //GROUP_NO
	private int memberNo; //MEMBER_NO
	private String feedContent; //FEED_CONTENT
	private Date feedWriteDate; //FEED_WRITE_DATE
	private String feedFileNo; //FEED_FILE_NO
	private String feedFilePath; //FEED_FILE_PATH
	
	public FeedOne() {
		
	}

	public FeedOne(int feedNo, int groupNo, int memberNo, String feedContent, Date feedWriteDate, String feedFileNo,
			String feedFilePath) {
		super();
		this.feedNo = feedNo;
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.feedContent = feedContent;
		this.feedWriteDate = feedWriteDate;
		this.feedFileNo = feedFileNo;
		this.feedFilePath = feedFilePath;
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

	public String getFeedFileNo() {
		return feedFileNo;
	}

	public void setFeedFileNo(String feedFileNo) {
		this.feedFileNo = feedFileNo;
	}

	public String getFeedFilePath() {
		return feedFilePath;
	}

	public void setFeedFilePath(String feedFilePath) {
		this.feedFilePath = feedFilePath;
	}

	@Override
	public String toString() {
		return "FeedOne [feedNo=" + feedNo + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", feedContent="
				+ feedContent + ", feedWriteDate=" + feedWriteDate + ", feedFileNo=" + feedFileNo + ", feedFilePath="
				+ feedFilePath + "]";
	}
	
}
