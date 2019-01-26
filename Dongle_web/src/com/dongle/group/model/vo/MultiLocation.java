package com.dongle.group.model.vo;

public class MultiLocation {
	private int groupNo;
	private String locCtgCode;
	
	
	public MultiLocation() {
		super();
	}

	public MultiLocation(int groupNo, String locCtgCode) {
		super();
		this.groupNo = groupNo;
		this.locCtgCode = locCtgCode;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getLocCtgCode() {
		return locCtgCode;
	}

	public void setLocCtgCode(String locCtgCode) {
		this.locCtgCode = locCtgCode;
	}

	
	@Override
	public String toString() {
		return "MultiLocation [groupNo=" + groupNo + ", locCtgCode=" + locCtgCode + "]";
	}
	
	
	
}
