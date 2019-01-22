package com.dongle.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo; //MEMBER_NO
	private String memberId; //MEMBER_ID
	private String memberPwd; //MEMBER_PWD
	private String memberName; //MEMBER_NAME
	private String gender; //MEMBER_GEN
	private String ssn; //MEMBER_SSN
	private String phone; //MEMBER_PHONE
	private String address; //MEMBER_ADDRESS
	private String email; //MEMBER_EMAIL
	private Date enrollDate; //MEMBER_ENROLL_DATE
	private String blackList; //BLACKLIST_YN
	private int reportCount;
	private String pwdHintList;
	private String pwdHintAnswer;
	
	public Member() {
		
	}

	public Member(int memberNo, String memberId, String memberPwd, String memberName, String gender, String ssn,
			String phone, String address, String email, Date enrollDate, String blackList, int reportCount,
			String pwdHintList, String pwdHintAnswer) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.ssn = ssn;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.enrollDate = enrollDate;
		this.blackList = blackList;
		this.reportCount = reportCount;
		this.pwdHintList = pwdHintList;
		this.pwdHintAnswer = pwdHintAnswer;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getBlackList() {
		return blackList;
	}

	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getPwdHintList() {
		return pwdHintList;
	}

	public void setPwdHintList(String pwdHintList) {
		this.pwdHintList = pwdHintList;
	}

	public String getPwdHintAnswer() {
		return pwdHintAnswer;
	}

	public void setPwdHintAnswer(String pwdHintAnswer) {
		this.pwdHintAnswer = pwdHintAnswer;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", gender=" + gender + ", ssn=" + ssn + ", phone=" + phone + ", address=" + address
				+ ", email=" + email + ", enrollDate=" + enrollDate + ", blackList=" + blackList + ", reportCount="
				+ reportCount + ", pwdHintList=" + pwdHintList + ", pwdHintAnswer=" + pwdHintAnswer + "]";
	}
	
	

	
}
