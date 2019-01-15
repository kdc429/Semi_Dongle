package com.dongle.board.model.vo;

import java.sql.Date;

public class BoardComment {
	
	private int groupNo;
	private int boarNo;
	private int boCommentNo;
	private int memberNo;
	private String boCommentContent;
	private Date boCommentDate;
	private int boCommentLevel;
	private int boCommentRef;
	private String groupMemberNickname;
	
	public BoardComment() {}

	public BoardComment(int groupNo, int boarNo, int boCommentNo, int memberNo, String boCommentContent,
			Date boCommentDate, int boCommentLevel, int boCommentRef, String groupMemberNickname) {
		super();
		this.groupNo = groupNo;
		this.boarNo = boarNo;
		this.boCommentNo = boCommentNo;
		this.memberNo = memberNo;
		this.boCommentContent = boCommentContent;
		this.boCommentDate = boCommentDate;
		this.boCommentLevel = boCommentLevel;
		this.boCommentRef = boCommentRef;
		this.groupMemberNickname = groupMemberNickname;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getBoarNo() {
		return boarNo;
	}

	public void setBoarNo(int boarNo) {
		this.boarNo = boarNo;
	}

	public int getBoCommentNo() {
		return boCommentNo;
	}

	public void setBoCommentNo(int boCommentNo) {
		this.boCommentNo = boCommentNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getBoCommentContent() {
		return boCommentContent;
	}

	public void setBoCommentContent(String boCommentContent) {
		this.boCommentContent = boCommentContent;
	}

	public Date getBoCommentDate() {
		return boCommentDate;
	}

	public void setBoCommentDate(Date boCommentDate) {
		this.boCommentDate = boCommentDate;
	}

	public int getBoCommentLevel() {
		return boCommentLevel;
	}

	public void setBoCommentLevel(int boCommentLevel) {
		this.boCommentLevel = boCommentLevel;
	}

	public int getBoCommentRef() {
		return boCommentRef;
	}

	public void setBoCommentRef(int boCommentRef) {
		this.boCommentRef = boCommentRef;
	}

	public String getGroupMemberNickname() {
		return groupMemberNickname;
	}

	public void setGroupMemberNickname(String groupMemberNickname) {
		this.groupMemberNickname = groupMemberNickname;
	}

	@Override
	public String toString() {
		return "BoardComment [groupNo=" + groupNo + ", boarNo=" + boarNo + ", boCommentNo=" + boCommentNo
				+ ", memberNo=" + memberNo + ", boCommentContent=" + boCommentContent + ", boCommentDate="
				+ boCommentDate + ", boCommentLevel=" + boCommentLevel + ", boCommentRef=" + boCommentRef
				+ ", groupMemberNickname=" + groupMemberNickname + "]";
	}

	}
