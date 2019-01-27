package com.dongle.group.model.vo;

import java.util.Date;

public class ListGroup {
	
	private String dongleName; //GROUP_NAME
	private String managerId; //MEMBER_ID
	private String topic; //TOPIC_CTG_CODE
	private String address; //LOC_METRO_CODE
	private String activeDate; //GROUP_DATE_CTG
	private int minAge; //MIN_AGE
	private int maxAge; //MAX_AGE
	private Date enrollDate; //GROUP_ENROLL_DATE
	private int reportCnt; //REPORT_GROUP_COUNT
	
	public ListGroup() {}

	public ListGroup(String dongleName, String managerId, String topic, String address, String activeDate, int minAge,
			int maxAge, Date enrollDate, int reportCnt) {
		super();
		this.dongleName = dongleName;
		this.managerId = managerId;
		this.topic = topic;
		this.address = address;
		this.activeDate = activeDate;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.enrollDate = enrollDate;
		this.reportCnt = reportCnt;
	}

	public String getDongleName() {
		return dongleName;
	}

	public void setDongleName(String dongleName) {
		this.dongleName = dongleName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
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

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getReportCnt() {
		return reportCnt;
	}

	public void setReportCnt(int reportCnt) {
		this.reportCnt = reportCnt;
	}

	@Override
	public String toString() {
		return "ListGroup [dongleName=" + dongleName + ", managerId=" + managerId + ", topic=" + topic + ", address="
				+ address + ", activeDate=" + activeDate + ", minAge=" + minAge + ", maxAge=" + maxAge + ", enrollDate="
				+ enrollDate + ", reportCnt=" + reportCnt + "]";
	}

	
}

	


