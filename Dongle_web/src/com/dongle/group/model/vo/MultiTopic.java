package com.dongle.group.model.vo;

public class MultiTopic {
	private int groupNo;
	private String topicCtgCode;
	public MultiTopic() {
		super();
	}
	public MultiTopic(int groupNo, String topicCtgCode) {
		super();
		this.groupNo = groupNo;
		this.topicCtgCode = topicCtgCode;
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
	
	@Override
	public String toString() {
		return "MultiTopic [groupNo=" + groupNo + ", topicCtgCode=" + topicCtgCode + "]";
	}
	
	
}
