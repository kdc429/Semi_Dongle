package com.dongle.member.model.vo;

public class DongleRptMember {
	private int memberNo; //MEMBER_NO
	private int groupNo; //GROUP_NO
	private String memberId; //MEMBER_ID
	private String memberNickname; //GROUP_MEMBER_NICKNAME
	private String rptCode; //REPORT_CODE
	private String rptReason; //REPORT_REASON
	private int rptCount; //REPORT_DONGLE_COUNT
	private String isBlack; //GROUP_BLACKLIST_YN
	
	public DongleRptMember() {}
	public DongleRptMember(String memberId, String memberNickname, String rptCode, String rptReason, int rptCount,
			String isBlack) {
		super();
		this.memberId = memberId;
		this.memberNickname = memberNickname;
		this.rptCode = rptCode;
		this.rptReason = rptReason;
		this.rptCount = rptCount;
		this.isBlack = isBlack;
	}
	
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getRptCode() {
		return rptCode;
	}
	public void setRptCode(String rptCode) {
		this.rptCode = rptCode;
	}
	public String getRptReason() {
		return rptReason;
	}
	public void setRptReason(String rptReason) {
		this.rptReason = rptReason;
	}
	public int getRptCount() {
		return rptCount;
	}
	public void setRptCount(int rptCount) {
		this.rptCount = rptCount;
	}
	public String getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}
	@Override
	public String toString() {
		return "DongleRptMember [memberNo=" + memberNo + ", groupNo=" + groupNo + ", memberId=" + memberId
				+ ", memberNickname=" + memberNickname + ", rptCode=" + rptCode + ", rptReason=" + rptReason
				+ ", rptCount=" + rptCount + ", isBlack=" + isBlack + "]";
	}
	
	
	
}
