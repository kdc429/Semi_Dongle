package com.dongle.feed.model.vo;

public class FeedNoResult {
	
	private int feedResult;
	private int feedNo;
	
	public FeedNoResult() {
		
	}

	public FeedNoResult(int feedResult, int feedNo) {
		super();
		this.feedResult = feedResult;
		this.feedNo = feedNo;
	}

	public int getFeedResult() {
		return feedResult;
	}

	public void setFeedResult(int feedResult) {
		this.feedResult = feedResult;
	}

	public int getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(int feedNo) {
		this.feedNo = feedNo;
	}

	@Override
	public String toString() {
		return "FeedNoResult [feedResult=" + feedResult + ", feedNo=" + feedNo + "]";
	}
	
	

}
