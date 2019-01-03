package com.dongle.member.model.vo;

import java.sql.Date;

public class Member {
	private String memberNo;
	private String memberId;
	private String memberPwd;
	private String gender;
	private String ssn;
	private String phone;
	private String address;
	private String email;
	private Date enrollDate;
	private int blackList;
	private int reportCount;
	
	public Member() {
		
	}
	

	public Member(String memberNo, String memberId, String memberPwd, String gender, String ssn, String phone,
			String address, String email, Date enrollDate, int blackList, int reportCount) {
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
	}


	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
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
	
}
