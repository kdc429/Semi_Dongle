package com.dongle.feed.model.vo;

public class FileList {
	
	private int feedNo;
	private String feedOriFilePath;
	private String feedRenameFilePath;
	
	public FileList() {
		
	}

	public FileList(int feedNo, String feedOriFilePath, String feedRenameFilePath) {
		super();
		this.feedNo = feedNo;
		this.feedOriFilePath = feedOriFilePath;
		this.feedRenameFilePath = feedRenameFilePath;
	}

	public int getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(int feedNo) {
		this.feedNo = feedNo;
	}

	public String getFeedOriFilePath() {
		return feedOriFilePath;
	}

	public void setFeedOriFilePath(String feedOriFilePath) {
		this.feedOriFilePath = feedOriFilePath;
	}

	public String getFeedRenameFilePath() {
		return feedRenameFilePath;
	}

	public void setFeedRenameFilePath(String feedRenameFilePath) {
		this.feedRenameFilePath = feedRenameFilePath;
	}

	@Override
	public String toString() {
		return "FileList [feedNo=" + feedNo + ", feedOriFilePath=" + feedOriFilePath + ", feedRenameFilePath="
				+ feedRenameFilePath + "]";
	}
	

	
	
	
	
}
