package com.dongle.group.model.vo;

public class MultiLocationName {
	private int groupNo;
	private String locCtgCode;
	private String locCtgName;
	
	public MultiLocationName() {
		
	}

	public MultiLocationName(int groupNo, String locCtgCode, String locCtgName) {
		super();
		this.groupNo = groupNo;
		this.locCtgCode = locCtgCode;
		this.locCtgName = locCtgName;
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

	public String getLocCtgName() {
		return locCtgName;
	}

	public void setLocCtgName(String locCtgName) {
		this.locCtgName = locCtgName;
	}

	@Override
	public String toString() {
		return "MultiLocationName [groupNo=" + groupNo + ", locCtgCode=" + locCtgCode + ", locCtgName=" + locCtgName
				+ "]";
	}
	
	
}
