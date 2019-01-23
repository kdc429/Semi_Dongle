package com.dongle.feed.model.vo;

public class FeedFile {
	
	private int feedNo;
	private int groupNo;
	private int feedFileNo; //FEED_FILE_NO
	private String feedOldFilePath; //FEED_OLD_FILE_PATH
	private String feedNewFilePath; //FEED_NEW_FILE_PATH
	
	public FeedFile() {
		
	}

	

	public FeedFile(int feedNo, int groupNo, int feedFileNo, String feedOldFilePath, String feedNewFilePath) {
		super();
		this.feedNo = feedNo;
		this.groupNo = groupNo;
		this.feedFileNo = feedFileNo;
		this.feedOldFilePath = feedOldFilePath;
		this.feedNewFilePath = feedNewFilePath;
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



	public int getFeedFileNo() {
		return feedFileNo;
	}



	public void setFeedFileNo(int feedFileNo) {
		this.feedFileNo = feedFileNo;
	}



	public String getFeedOldFilePath() {
		return feedOldFilePath;
	}



	public void setFeedOldFilePath(String feedOldFilePath) {
		this.feedOldFilePath = feedOldFilePath;
	}



	public String getFeedNewFilePath() {
		return feedNewFilePath;
	}



	public void setFeedNewFilePath(String feedNewFilePath) {
		this.feedNewFilePath = feedNewFilePath;
	}



	@Override
	public String toString() {
		return "FeedFile [feedNo=" + feedNo + ", groupNo=" + groupNo + ", feedFileNo=" + feedFileNo
				+ ", feedOldFilePath=" + feedOldFilePath + ", feedNewFilePath=" + feedNewFilePath + "]";
	}

	
	
	
	
	
}
