package com.dongle.group.model.vo;

public class GroupMemberCount {
	
	private int groupNo;
	private int memberCount;
	
	public GroupMemberCount() {
		
	}

	public GroupMemberCount(int groupNo, int memberCount) {
		super();
		this.groupNo = groupNo;
		this.memberCount = memberCount;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	@Override
	public String toString() {
		return "GroupMemberCount [groupNo=" + groupNo + ", memberCount=" + memberCount + "]";
	}
	
	

}
