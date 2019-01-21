package com.dongle.feed.model.vo;

import java.sql.Date;

public class FeedComment {
	
	private int groupNo; //GROUP_NO
	private int feedNo; //FEED_NO
	private int feCommentNo; //FE_COMMENT_NO
	private int feCommentLevel; //FE_COMMENT_LEVEL
	private int memberNo; //MEMBER_NO
	private String feCommentContent; //FE_COMMENT_CONTENT
	private Date feCommentDate; //FE_COMMENT_DATE
	private int feCommentRef; //FE_COMMENT_REF
	
	public FeedComment() {
		
	}

	
	

	public FeedComment(int groupNo, int feedNo, int feCommentNo, int feCommentLevel, int memberNo,
			String feCommentContent, Date feCommentDate, int feCommentRef) {
		super();
		this.groupNo = groupNo;
		this.feedNo = feedNo;
		this.feCommentNo = feCommentNo;
		this.feCommentLevel = feCommentLevel;
		this.memberNo = memberNo;
		this.feCommentContent = feCommentContent;
		this.feCommentDate = feCommentDate;
		this.feCommentRef = feCommentRef;
	}




	public int getFeCommentRef() {
		return feCommentRef;
	}




	public void setFeCommentRef(int feCommentRef) {
		this.feCommentRef = feCommentRef;
	}




	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(int feedNo) {
		this.feedNo = feedNo;
	}

	public int getFeCommentNo() {
		return feCommentNo;
	}

	public void setFeCommentNo(int feCommentNo) {
		this.feCommentNo = feCommentNo;
	}

	public int getFeCommentLevel() {
		return feCommentLevel;
	}

	public void setFeCommentLevel(int feCommentLevel) {
		this.feCommentLevel = feCommentLevel;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getFeCommentContent() {
		return feCommentContent;
	}

	public void setFeCommentContent(String feCommentContent) {
		this.feCommentContent = feCommentContent;
	}

	public Date getFeCommentDate() {
		return feCommentDate;
	}

	public void setFeCommentDate(Date feCommentDate) {
		this.feCommentDate = feCommentDate;
	}

	@Override
	public String toString() {
		return "FeedComment [groupNo=" + groupNo + ", feedNo=" + feedNo + ", feCommentNo=" + feCommentNo
				+ ", feCommentLevel=" + feCommentLevel + ", memberNo=" + memberNo + ", feCommentContent="
				+ feCommentContent + ", feCommentDate=" + feCommentDate + "]";
	}
	
	

}
