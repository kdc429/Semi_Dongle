package com.dongle.group.model.vo;

public class TopicCtg {
	private String topicCtgCode; //TOPIC_CTG_CODE
	private String topicCtgName; //TOPIC_CTG_NAME
	
	public TopicCtg() {
		super();
	}
	public TopicCtg(String topicCtgCode, String topicCtgName) {
		super();
		this.topicCtgCode = topicCtgCode;
		this.topicCtgName = topicCtgName;
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
		return "TopicCtg [topicCtgCode=" + topicCtgCode + ", topicCtgName=" + topicCtgName + "]";
	}
	
	
}
