package com.dongle.main.model.vo;

public class TopicCategory {
	private int topicCtgCode;//TOPIC_CTG_CODE
	private String topicCtgName;//TOPIC_CTG_CODE
	
	public TopicCategory(){
		
	}

	public TopicCategory(int topicCtgCode, String topicCtgName) {
		super();
		this.topicCtgCode = topicCtgCode;
		this.topicCtgName = topicCtgName;
	}

	public int getTopicCtgCode() {
		return topicCtgCode;
	}

	public void setTopicCtgCode(int topicCtgCode) {
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
		return "TopicCategory [topicCtgCode=" + topicCtgCode + ", topicCtgName=" + topicCtgName + "]";
	}
	
	
}
