package com.dongle.group.model.vo;

import java.util.Date;

public class GroupMember {

	private int groupNo;
	private int memberNo;
	private String groupMemberNickname;
	private String groupMemberImageOldPath;//GROUP_MEMBER_IMAGE_OLD_PATH VARCHAR2(100),
	private String groupMemberImageNewPath;//GROUP_MEMBER_IMAGE_NEW_PATH VARCHAR2(100),
	private Date groupMemberEnrollDate;
	private String blackListYN;
	private int reportDongleCount;
	
	public GroupMember() {}

	public GroupMember(int groupNo, int memberNo, String groupMemberNickname,
			String groupMemberImageOldPath, String groupMemberImageNewPath, Date groupMemberEnrollDate,
			String blackListYN, int reportDongleCount) {
		super();
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.groupMemberNickname = groupMemberNickname;
		this.groupMemberImageOldPath = groupMemberImageOldPath;
		this.groupMemberImageNewPath = groupMemberImageNewPath;
		this.groupMemberEnrollDate = groupMemberEnrollDate;
		this.blackListYN = blackListYN;
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

	public String getBlackListYN() {
		return blackListYN;
	}

	public void setBlackListYN(String blackistYN) {
		this.blackListYN = blackistYN;
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
				+ groupMemberNickname + ", groupMemberImageOldPath="
				+ groupMemberImageOldPath + ", groupMemberImageNewPath=" + groupMemberImageNewPath
				+ ", groupMemberEnrollDate=" + groupMemberEnrollDate + ", blackistYN=" + blackListYN
				+ ", reportDongleCount=" + reportDongleCount + "]";
	}
	

}
