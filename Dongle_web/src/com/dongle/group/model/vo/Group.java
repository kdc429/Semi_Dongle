package com.dongle.group.model.vo;

import java.sql.Date;

public class Group {
	
	
	private int memberNo; //MEMBER_NO
	private int groupNo; //GROUP_NO
	private String groupName; //GROUP_NAME
	private String topicCode; //TOPIC_CTG_CODE
	private String topicName; //TOPIC_CTG_NAME	
	private String locMetroName; //LOC_METRO_NAME
	private String locAreaName; //LOC_AREA_NAME
	private String locTownName; //LOC_TOWN_NAME
	private String locCtgCode; //LOC_CTG_CODE
	private String groupDateCtg; //GROUP_DATE_CTG
	private int minAge; //MIN_AGE
	private int maxAge; //MAX_AGE
	private String groupImageOldPath; //GROUP_IMAGE_OLD_PATH VARCHAR2(100),
	private String groupImageNewPath;//GROUP_IMAGE_NEW_PATH VARCHAR2(100),
	private String groupIntro; //GROUP_INTRODUCE
	private Date groupEnrollDate; //GROUP_ENROLL_DATE
	private int reportGroupCnt; //REPORT_GROUP_COUNT
	
	public Group() {
		
	}

	public Group(int memberNo, int groupNo, String groupName, String topicCode, String topicName, String locMetroName,
			String locAreaName, String locTownName, String locCtgCode, String groupDateCtg, int minAge, int maxAge,
			String groupImageOldPath, String groupImageNewPath, String groupIntro, Date groupEnrollDate,
			int reportGroupCnt) {
		super();
		this.memberNo = memberNo;
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.topicCode = topicCode;
		this.topicName = topicName;
		this.locMetroName = locMetroName;
		this.locAreaName = locAreaName;
		this.locTownName = locTownName;
		this.locCtgCode = locCtgCode;
		this.groupDateCtg = groupDateCtg;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.groupImageOldPath = groupImageOldPath;
		this.groupImageNewPath = groupImageNewPath;
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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getLocMetroName() {
		return locMetroName;
	}

	public void setLocMetroName(String locMetroName) {
		this.locMetroName = locMetroName;
	}

	public String getLocAreaName() {
		return locAreaName;
	}

	public void setLocAreaName(String locAreaName) {
		this.locAreaName = locAreaName;
	}

	public String getLocTownName() {
		return locTownName;
	}

	public void setLocTownName(String locTownName) {
		this.locTownName = locTownName;
	}

	public String getLocCtgCode() {
		return locCtgCode;
	}

	public void setLocCtgCode(String locCtgCode) {
		this.locCtgCode = locCtgCode;
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

	public String getGroupImageOldPath() {
		return groupImageOldPath;
	}

	public void setGroupImageOldPath(String groupImageOldPath) {
		this.groupImageOldPath = groupImageOldPath;
	}

	public String getGroupImageNewPath() {
		return groupImageNewPath;
	}

	public void setGroupImageNewPath(String groupImageNewPath) {
		this.groupImageNewPath = groupImageNewPath;
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

	@Override
	public String toString() {
		return "Group [memberNo=" + memberNo + ", groupNo=" + groupNo + ", groupName=" + groupName + ", topicCode="
				+ topicCode + ", topicName=" + topicName + ", locMetroName=" + locMetroName + ", locAreaName="
				+ locAreaName + ", locTownName=" + locTownName + ", locCtgCode=" + locCtgCode + ", groupDateCtg="
				+ groupDateCtg + ", minAge=" + minAge + ", maxAge=" + maxAge + ", groupImageOldPath="
				+ groupImageOldPath + ", groupImageNewPath=" + groupImageNewPath + ", groupIntro=" + groupIntro
				+ ", groupEnrollDate=" + groupEnrollDate + ", reportGroupCnt=" + reportGroupCnt + "]";
	}
	
}
