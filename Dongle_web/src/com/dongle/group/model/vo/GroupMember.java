package com.dongle.group.model.vo;

import java.util.Date;

public class GroupMember {
	
	private int groupNo;
	private int memberNo;
	private String groupMemberNickname;
	private String groupMemberImageOldPath;
	private String groupMemberImageNewPath;
	private Date groupMemberEnrollDate;
	private String blacklistYN;
	private int reportDongleCount;
	
	public GroupMember() {
		super();
	}

	public GroupMember(int groupNo, int memberNo, String groupMemberNickname, String groupMemberImageOldPath,
			String groupMemberImageNewPath, Date groupMemberEnrollDate, String blacklistYN, int reportDongleCount) {
		super();
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.groupMemberNickname = groupMemberNickname;
		this.groupMemberImageOldPath = groupMemberImageOldPath;
		this.groupMemberImageNewPath = groupMemberImageNewPath;
		this.groupMemberEnrollDate = groupMemberEnrollDate;
		this.blacklistYN = blacklistYN;
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

	public String getGroupMemberImageOldPath() {
		return groupMemberImageOldPath;
	}

	public void setGroupMemberImageOldPath(String groupMemberImageOldPath) {
		this.groupMemberImageOldPath = groupMemberImageOldPath;
	}

	public String getGroupMemberImageNewPath() {
		return groupMemberImageNewPath;
	}

	public void setGroupMemberImageNewPath(String groupMemberImageNewPath) {
		this.groupMemberImageNewPath = groupMemberImageNewPath;
	}

	public Date getGroupMemberEnrollDate() {
		return groupMemberEnrollDate;
	}

	public void setGroupMemberEnrollDate(Date groupMemberEnrollDate) {
		this.groupMemberEnrollDate = groupMemberEnrollDate;
	}

	public String getBlacklistYN() {
		return blacklistYN;
	}

	public void setBlacklistYN(String blacklistYN) {
		this.blacklistYN = blacklistYN;
	}

	public int getReportDongleCount() {
		return reportDongleCount;
	}

	public void setReportDongleCount(int reportDongleCount) {
		this.reportDongleCount = reportDongleCount;
	}

	@Override
	public String toString() {
		return "GroupMember [groupNo=" + groupNo + ", memberNo=" + memberNo + ", groupMemberNickname="
				+ groupMemberNickname + ", groupMemberImageOldPath=" + groupMemberImageOldPath
				+ ", groupMemberImageNewPath=" + groupMemberImageNewPath + ", groupMemberEnrollDate="
				+ groupMemberEnrollDate + ", blacklistYN=" + blacklistYN + ", reportDongleCount=" + reportDongleCount
				+ "]";
	}

}
