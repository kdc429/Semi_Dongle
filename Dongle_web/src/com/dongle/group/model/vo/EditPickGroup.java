package com.dongle.group.model.vo;

public class EditPickGroup {
	
	private int groupNo; //GROUP_NO
	private String editFilePath; //EDIT_FILE_PATH
	private String editContent; //EDIT_CONTENT
	
	public EditPickGroup() {
		
	}

	public EditPickGroup(int groupNo, String editFilePath, String editContent) {
		super();
		this.groupNo = groupNo;
		this.editFilePath = editFilePath;
		this.editContent = editContent;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getEditFilePath() {
		return editFilePath;
	}

	public void setEditFilePath(String editFilePath) {
		this.editFilePath = editFilePath;
	}

	public String getEditContent() {
		return editContent;
	}

	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}

	@Override
	public String toString() {
		return "EditPickGroup [groupNo=" + groupNo + ", editFilePath=" + editFilePath + ", editContent=" + editContent
				+ "]";
	}
	

}
