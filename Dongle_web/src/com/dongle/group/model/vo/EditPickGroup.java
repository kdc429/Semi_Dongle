package com.dongle.group.model.vo;

public class EditPickGroup {
	
	private int groupNo; //GROUP_NO
	private String editOldFilePath; //EDIT_FILE_PATH
	private String editNewFilePath;
	private String editContent; //EDIT_CONTENT
	
	public EditPickGroup() {
		
	}

	public EditPickGroup(int groupNo, String editOldFilePath, String editNewFilePath, String editContent) {
		super();
		this.groupNo = groupNo;
		this.editOldFilePath = editOldFilePath;
		this.editNewFilePath = editNewFilePath;
		this.editContent = editContent;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getEditOldFilePath() {
		return editOldFilePath;
	}

	public void setEditOldFilePath(String editOldFilePath) {
		this.editOldFilePath = editOldFilePath;
	}

	public String getEditNewFilePath() {
		return editNewFilePath;
	}

	public void setEditNewFilePath(String editNewFilePath) {
		this.editNewFilePath = editNewFilePath;
	}

	public String getEditContent() {
		return editContent;
	}

	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}

	@Override
	public String toString() {
		return "EditPickGroup [groupNo=" + groupNo + ", editOldFilePath=" + editOldFilePath + ", editNewFilePath="
				+ editNewFilePath + ", editContent=" + editContent + "]";
	}
	


}
