package com.dongle.group.model.vo;

import java.util.Date;

public class GroupMember {
	private int groupNo;
	private int memberNo;
	private String groupMemberNickname;
	private String groupMemberImagePath;
	private Date groupMemberEnrollDate;
	private String blackistYN;
	private int reportDongleCount;
	
	public GroupMember() {}
	
	public GroupMember(int groupNo, int memberNo, String groupMemberNickname, String groupMemberImagePath,
			Date groupMemberEnrollDate, String blackistYN, int reportDongleCount) {
		super();
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.groupMemberNickname = groupMemberNickname;
		this.groupMemberImagePath = groupMemberImagePath;
		this.groupMemberEnrollDate = groupMemberEnrollDate;
		this.blackistYN = blackistYN;
		this.reportDongleCount = reportDongleCount;
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
	public String getGroupMemberNickname() {
		return groupMemberNickname;
	}
	public void setGroupMemberNickname(String groupMemberNickname) {
		this.groupMemberNickname = groupMemberNickname;
	}
	public String getGroupMemberImagePath() {
		return groupMemberImagePath;
	}
	public void setGroupMemberImagePath(String groupMemberImagePath) {
		this.groupMemberImagePath = groupMemberImagePath;
	}
	public Date getGroupMemberEnrollDate() {
		return groupMemberEnrollDate;
	}
	public void setGroupMemberEnrollDate(Date groupMemberEnrollDate) {
		this.groupMemberEnrollDate = groupMemberEnrollDate;
	}
	public String getBlackistYN() {
		return blackistYN;
	}
	public void setBlackistYN(String blackistYN) {
		this.blackistYN = blackistYN;
	}
	public int getReportDongleCount() {
		return reportDongleCount;
	}
	public void setReportDongleCount(int reportDongleCount) {
		this.reportDongleCount = reportDongleCount;
	}

	@Override
	public String toString() {
		return "GroupMember테이블 [groupNo=" + groupNo + ", memberNo=" + memberNo + ", groupMemberNickname="
				+ groupMemberNickname + ", groupMemberImagePath=" + groupMemberImagePath + ", groupMemberEnrollDate="
				+ groupMemberEnrollDate + ", blackistYN=" + blackistYN + ", reportDongleCount=" + reportDongleCount
				+ "]";
	}
	
	
}
