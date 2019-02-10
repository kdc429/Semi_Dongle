package com.dongle.group.model.vo;

public class MultiTopicName {
	private int groupNo;
	private String topicCtgCode;
	private String topicCtgName;
	
	public MultiTopicName() {
		
	}

	public MultiTopicName(int groupNo, String topicCtgCode, String topicCtgName) {
		super();
		this.groupNo = groupNo;
		this.topicCtgCode = topicCtgCode;
		this.topicCtgName = topicCtgName;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getTopicCtgCode() {
		return topicCtgCode;
	}

	public void setTopicCtgCode(String topicCtgCode) {
		this.topicCtgCode = topicCtgCode;
	}

	public String getTopicCtgName() {
		return topicCtgName;
	}

	public void setTopicCtgName(String topicCtgName) {
		this.topicCtgName = topicCtgName;
	}

	@Override
	public String toString() {
		return "MultiTopicName [groupNo=" + groupNo + ", topicCtgCode=" + topicCtgCode + ", topicCtgName="
				+ topicCtgName + "]";
	}
	
	
	
	
}
