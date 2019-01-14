package com.dongle.feed.model.vo;

public class FeedFile {
	
	private int feedNo;
	private int groupNo;
	private String feedFileNo;
	private String feedFilePath;
	
	public FeedFile() {
		
	}

	public FeedFile(int feedNo, int groupNo, String feedFileNo, String feedFilePath) {
		super();
		this.feedNo = feedNo;
		this.groupNo = groupNo;
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
		return "FeedFile [feedNo=" + feedNo + ", groupNo=" + groupNo + ", feedFileNo=" + feedFileNo + ", feedFilePath="
				+ feedFilePath + "]";
	}
	
	
}
