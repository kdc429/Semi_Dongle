package com.dongle.group.model.vo;

import java.sql.Date;

public class Group {
	private int memberNo; //MEMBER_NO
	private int groupNo; //GROUP_NO
	private String groupName; //GROUP_NAME
	private String topicCode; //TOPIC_CTG_CODE
	private String groupDateCtg; //GROUP_DATE_CTG
	private int minAge; //MIN_AGE
	private int maxAge; //MAX_AGE
	private String imgPath; //GROUP_IMAGE_PATH
	private String groupIntro; //GROUP_INTRODUCE
	private Date groupEnrollDate; //GROUP_ENROLL_DATE
	private int reportGroupCnt; //REPORT_GROUP_COUNT
	
	public Group() {
		
	}

	public Group(int memberNo, int groupNo, String groupName, String topicCode, String groupDateCtg, int minAge,
			int maxAge, String imgPath, String groupIntro, Date groupEnrollDate, int reportGroupCnt) {
		super();
		this.memberNo = memberNo;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.topicCode = topicCode;
		this.groupDateCtg = groupDateCtg;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.imgPath = imgPath;
		this.groupIntro = groupIntro;
		this.groupEnrollDate = groupEnrollDate;
		this.reportGroupCnt = reportGroupCnt;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getGroupDateCtg() {
		return groupDateCtg;
	}

	public void setGroupDateCtg(String groupDateCtg) {
		this.groupDateCtg = groupDateCtg;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getGroupIntro() {
		return groupIntro;
	}

	public void setGroupIntro(String groupIntro) {
		this.groupIntro = groupIntro;
	}

	public Date getGroupEnrollDate() {
		return groupEnrollDate;
	}

	public void setGroupEnrollDate(Date groupEnrollDate) {
		this.groupEnrollDate = groupEnrollDate;
	}

	public int getReportGroupCnt() {
		return reportGroupCnt;
	}

	public void setReportGroupCnt(int reportGroupCnt) {
		this.reportGroupCnt = reportGroupCnt;
	}
	
	
	
	
	
}
