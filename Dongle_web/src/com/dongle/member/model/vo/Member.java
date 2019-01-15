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
	private int blackList; //BLACKLIST_YN
	private int reportCount;
	private String memberImaPath; //MEMBER_IMG_PATH
	
	public Member() {
		
	}
	

	public Member(int memberNo, String memberId, String memberPwd, String gender, String ssn, String phone,
			String address, String email, Date enrollDate, int blackList, int reportCount, String memberImaPath) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.gender = gender;
		this.ssn = ssn;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.enrollDate = enrollDate;
		this.blackList = blackList;
		this.reportCount = reportCount;
		this.memberImaPath = memberImaPath;
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

	public int getBlackList() {
		return blackList;
	}

	public void setBlackList(int blackList) {
		this.blackList = blackList;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}
	
	public String getMemberImaPath()
	{
		return memberImaPath;
	}
	
	public void setMemberImaPath(String memberImaPath) {
		this.memberImaPath = memberImaPath;
	}
	
	
}
